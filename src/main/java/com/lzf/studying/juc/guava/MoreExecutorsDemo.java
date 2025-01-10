package com.lzf.studying.juc.guava;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.lzf.studying.juc.util.SleepUtil;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import lombok.extern.slf4j.Slf4j;

/**
 * @author leizefeng
 */
@Slf4j
public class MoreExecutorsDemo {

  public static void main(String[] args) {
    MoreExecutorsDemo demo = new MoreExecutorsDemo();
    demo.demo1();
//    SleepUtil.sleep();
//    demo.demo2();
    SleepUtil.sleep();
    demo.demo3();
  }

  private void demo1() {
    Executor executor = MoreExecutors.directExecutor();
    AtomicBoolean executed = new AtomicBoolean();
    executor.execute(() -> executed.set(true));
    log.info("executed: {}", executed.get());
  }

  private void demo2() {
    ThreadPoolExecutor executor =
        (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
    ExecutorService executorService = MoreExecutors.getExitingExecutorService(
        executor, 3000, TimeUnit.MILLISECONDS);
    executorService.execute(() -> {
      while (true) {
        log.info("running");
        SleepUtil.sleep(500);
      }
    });
  }

  private void demo3() {
    ExecutorService pool = Executors.newCachedThreadPool();
    ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(pool);
    ListenableFuture<String> future1 = listeningExecutorService.submit(() -> {
      SleepUtil.sleep(500);
      log.info("the first task");
      return "the first result";
    });
    ListenableFuture<String> future2 = listeningExecutorService.submit(() -> {
      SleepUtil.sleep(1000);
      log.info("the second task");
      return "the second result";
    });
    try {
      log.info(
          "result: {}", String.join(", ", Futures.allAsList(future1, future2).get()));
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }
}
