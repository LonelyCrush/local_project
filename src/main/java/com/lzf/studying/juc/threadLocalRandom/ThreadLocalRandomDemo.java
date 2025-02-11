package com.lzf.studying.juc.threadLocalRandom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author leizefeng
 */
public class ThreadLocalRandomDemo {

  public static void main(String[] args) throws InterruptedException {
    ThreadLocalRandomDemo demo = new ThreadLocalRandomDemo();

    // 测量 test1 的执行时间
    long startTime1 = System.currentTimeMillis();
    demo.test1();
    long endTime1 = System.currentTimeMillis();
    long duration1 = (endTime1 - startTime1);  // 总耗时，单位为纳秒

    // 测量 test2 的执行时间
    long startTime2 = System.currentTimeMillis();
    demo.test2();
    long endTime2 = System.currentTimeMillis();
    long duration2 = (endTime2 - startTime2);  // 总耗时，单位为纳秒

    System.out.println("test1 执行时间: " + duration1 + " 毫秒");
    System.out.println("test2 执行时间: " + duration2 + " 毫秒");

  }

  private void test1() throws InterruptedException {
    ExecutorService executor = Executors.newWorkStealingPool();
    List<Callable<Integer>> callableList = new ArrayList<>();
    Random random = new Random();
    for (int i = 0; i < 1000; i++) {
      callableList.add(random::nextInt);
    }
    executor.invokeAll(callableList);
    executor.shutdown();
  }

  private void test2() throws InterruptedException {
    ExecutorService executor = Executors.newWorkStealingPool();
    List<Callable<Integer>> callableList = new ArrayList<>();
    for (int i = 0; i < 1000; i++) {
      callableList.add(() -> ThreadLocalRandom.current().nextInt());
    }
    executor.invokeAll(callableList);
    executor.shutdown();
  }
}
