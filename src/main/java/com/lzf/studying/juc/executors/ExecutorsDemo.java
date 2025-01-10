package com.lzf.studying.juc.executors;

import cn.hutool.core.thread.NamedThreadFactory;
import com.lzf.studying.juc.util.SleepUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.jetbrains.annotations.NotNull;

/**
 * @author leizefeng
 * <p> FixedThreadPool 和 SingleThreadPool:
 * <p> 允许的请求队列长度为 Integer.MAX_VALUE，可能会堆积大量的请求，从而导致 OOM
 * <p> CachedThreadPool 和 ScheduledThreadPool:
 * <p> 允许的创建线程数量为 Integer.MAX_VALUE，可能会创建大量的线程，从而导致 OOM。
 */
public class ExecutorsDemo {

  public static void main(String[] args) {
    ExecutorsDemo demo = new ExecutorsDemo();

    demo.fixedPoolDefaultFactory();
//    demo.fixedPoolNamedFactory();
//    demo.cachedPool();
//    demo.singlePool();
//    demo.scheduledPool();
//    demo.singleScheduledPool();
  }

  private void fixedPoolDefaultFactory() {
    // 一个可以重用固定线程数的线程池来处理负载较重的服务器任务
    ExecutorService pool = Executors.newFixedThreadPool(2);

    // execute()方法用于提交一个 Runnable 任务到线程池中
    executeTask(pool);

    // submit()方法用于提交一个 Runnable 或 Callable 任务到线程池中
    System.out.println();
    SleepUtil.sleep();
    pool = Executors.newFixedThreadPool(2);
    submitTask(pool);

    // invokeAny()方法用于提交一组 Callable 任务到线程池中
    // invokeAll()方法用于提交一组 Callable 任务到线程池中
    System.out.println();
    SleepUtil.sleep();
    pool = Executors.newFixedThreadPool(2);
    invokeTask(pool);
  }

  private void executeTask(ExecutorService pool) {
    for (int i = 1; i < 4; i++) {
      int finalI = i;
      pool.execute(() -> {
        System.out.println("线程 " + Thread.currentThread().getName() + " 正在执行任务" + finalI);
        SleepUtil.sleep();
        System.out.println("线程 " + Thread.currentThread().getName() + " 完成任务" + finalI);
      });
    }
    // 等待所有线程执行完毕
    pool.shutdown();
    // 会尝试立即销毁， 返回等待处理的任务列表
    SleepUtil.sleep();
    List<Runnable> toDoTasks = pool.shutdownNow();
    System.out.println("剩余任务数：" + toDoTasks.size());
    if (!toDoTasks.isEmpty()) {
      for (Runnable toDoTask : toDoTasks) {
        toDoTask.run();
      }
    }
  }

  private void submitTask(@NotNull ExecutorService pool) {
    // Runnable
    Future<?> future1 = pool.submit(() -> {
      System.out.println("线程 " + Thread.currentThread().getName() + " 正在执行任务");
      SleepUtil.sleep();
      System.out.println("线程 " + Thread.currentThread().getName() + " 完成任务");
    });
    // Callable
    Future<String> future2 = pool.submit(() -> {
      System.out.println("线程 " + Thread.currentThread().getName() + " 正在执行任务");
      SleepUtil.sleep();
      System.out.println("线程 " + Thread.currentThread().getName() + " 完成任务");
      return "任务执行完成";
    });
    try {
      // Future 是异步阻塞的
      System.out.println(future1.get(2000, TimeUnit.MILLISECONDS));
      System.out.println(future2.get(2000, TimeUnit.MILLISECONDS));
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    } catch (TimeoutException e) {
      System.out.println("任务还未完成");
    }
    try {
      System.out.println("任务1结果：" + future1.get(1100, TimeUnit.MILLISECONDS));
      System.out.println("任务2结果：" + future2.get(1100, TimeUnit.MILLISECONDS));
    } catch (InterruptedException | ExecutionException | TimeoutException e) {
      throw new RuntimeException(e);
    }
  }

  private void invokeTask(ExecutorService pool) {
    List<Callable<String>> callableList = new ArrayList<>();
    for(int i = 1; i < 4; i++) {
      int finalI = i;
      callableList.add(() -> {
        System.out.println("线程 " + Thread.currentThread().getName() + " 正在执行任务" + finalI);
        SleepUtil.sleep();
        System.out.println("线程 " + Thread.currentThread().getName() + " 完成任务" + finalI);
        return "任务" + finalI + "执行完成";
      });
    }
    try {
      // invokeAny()方法用于提交一组 Callable 任务到线程池中，并返回一个结果
      // 一旦某个任务完成，会立即返回结果，并且会尝试取消其他尚未完成的任务
      String result = pool.invokeAny(callableList);
      System.out.println("执行结果：" + result);
      // invokeAll()方法用于提交一组 Callable 任务到线程池中，并返回一个 List<Future<T>>
      // 会阻塞，直到所有任务完成，
      List<Future<String>> futureList = pool.invokeAll(callableList);
      for (Future<String> future : futureList) {
        System.out.println("任务执行结果：" + future.get());
      }
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
    pool.shutdown();
  }

  private void fixedPoolNamedFactory() {
    // 使用工厂可以为线程池中的线程命名以及决定是否为守护线程
    // 守护线程（Daemon Thread）是Java中的一种特殊线程，它主要用于在后台为其他线程提供服务。
    // 守护线程的特点是，当所有非守护线程（用户线程）结束时，守护线程会自动终止，即使它还在运行。
    NamedThreadFactory factory = new NamedThreadFactory("my-thread-pool", false);
    ExecutorService pool = Executors.newFixedThreadPool(2, factory);
    executeTask(pool);
  }

  private void cachedPool() {
    // 一个可以灵活调整线程数的线程池， 适用于执行大量短生命周期的任务
    ExecutorService pool = Executors.newCachedThreadPool();
    executeTask(pool);
  }

  private void singlePool() {
    // 一个单线程的线程池，适用于执行CPU密集型任务
    ExecutorService pool = Executors.newSingleThreadExecutor();
    executeTask(pool);
  }

  private void scheduledPool() {
    // 一个定时执行的线程池，适用于执行定时任务
    ScheduledExecutorService pool = Executors.newScheduledThreadPool(2);
    doScheduledTask(pool);
  }

  private void doScheduledTask(@NotNull ScheduledExecutorService pool) {
    for (int i = 1; i < 4; i++) {
      int finalI = i;
      pool.scheduleAtFixedRate(() -> {
        System.out.println("线程 " + Thread.currentThread().getName() + " 正在执行任务" + finalI);
        SleepUtil.sleep();
        System.out.println("线程 " + Thread.currentThread().getName() + " 完成任务" + finalI);
      }, 2, 10, TimeUnit.SECONDS);
//      pool.scheduleWithFixedDelay(() -> {
//        System.out.println("线程 " + Thread.currentThread().getName() + " 正在执行任务" + finalI);
//        sleep();
//        System.out.println("线程 " + Thread.currentThread().getName() + " 完成任务" + finalI);
//      }, 2, 10, TimeUnit.SECONDS);
    }
  }

  private void singleScheduledPool() {
    // 一个单线程的定时执行的线程池，适用于执行定时任务
    ScheduledExecutorService pool = Executors.newSingleThreadScheduledExecutor();
    doScheduledTask(pool);
  }
}
