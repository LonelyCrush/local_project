package com.lzf.local.d2410.d241017;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author leizefeng
 */
public class FutureTaskDemo {

  public static void main(String[] args) throws InterruptedException, ExecutionException {
    FutureTaskDemo demo = new FutureTaskDemo();
    demo.task1();
    demo.task2();
  }

  private void task1() throws InterruptedException {
    long start = System.currentTimeMillis();
    System.out.println("begin: ");

    Thread thread1 = new Thread(() -> {
      try {
        Thread.sleep(3000);
        System.out.println("thread1 cost 3 seconds");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "thread1");
    thread1.start();
    thread1.join();

    Thread thread2 = new Thread(() -> {
      try {
        Thread.sleep(2000);
        System.out.println("thread2 cost 2 seconds");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, "thread1");
    thread2.start();
    thread2.join();

    long end = System.currentTimeMillis();
    System.out.println("end: cost " + (end - start) + " milliseconds");
  }

  private void task2() throws InterruptedException, ExecutionException {
    long start = System.currentTimeMillis();
    System.out.println("begin: ");

    FutureTask<String> futureTask1 = new FutureTask<>(() -> {
      Thread.sleep(3000);
      return "thread1 cost 3 seconds";
    });
    new Thread(futureTask1).start();

    FutureTask<String> futureTask2 = new FutureTask<>(() -> {
      Thread.sleep(2000);
      return "thread2 cost 2 seconds";
    });
    new Thread(futureTask2).start();

    System.out.println(futureTask1.get());
    System.out.println(futureTask2.get());

    long end = System.currentTimeMillis();
    System.out.println("end: cost " + (end - start) + " milliseconds");
  }
}
