package com.lzf.studying.juc.countDownLatch;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author leizefeng
 */
@Slf4j
public class CountDownLatchDemo {

  public static void main(String[] args) throws InterruptedException {
    CountDownLatchDemo demo = new CountDownLatchDemo();

    demo.test1();
    demo.test2();
  }

  @AllArgsConstructor
  private static class Worker implements Runnable {
    private List<String> outputScraper;
    private CountDownLatch countDownLatch;

    @Override
    public void run() {
      outputScraper.add("count");
      countDownLatch.countDown();
    }
  }

  public void test1() throws InterruptedException {
    List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());
    CountDownLatch countDownLatch = new CountDownLatch(5);
    List<Thread> workerThreads = Stream.generate(() ->
        new Thread(new Worker(synchronizedList, countDownLatch)))
        .limit(5).collect(toList());

    workerThreads.forEach(Thread::start);
    countDownLatch.await();
    synchronizedList.add("All tasks completed");
    log.info("Output: {}", synchronizedList);
  }

  @AllArgsConstructor
  private static class WaitingWorker implements Runnable {
    private List<String> outputScraper;
    private CountDownLatch readyCountDownLatch;
    private CountDownLatch startCountDownLatch;
    private CountDownLatch endCountDownLatch;

    @Override
    public void run() {
      readyCountDownLatch.countDown();
      try {
        startCountDownLatch.await();
        outputScraper.add("count");
        endCountDownLatch.countDown();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public void test2() throws InterruptedException {
    List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());
    CountDownLatch readyCountDownLatch = new CountDownLatch(5);
    CountDownLatch startCountDownLatch = new CountDownLatch(1);
    CountDownLatch endCountDownLatch = new CountDownLatch(5);
    List<Thread> workerThreads = Stream.generate(() ->
        new Thread(new WaitingWorker(
            synchronizedList, readyCountDownLatch, startCountDownLatch, endCountDownLatch)))
        .limit(5).collect(toList());

    workerThreads.forEach(Thread::start);
    readyCountDownLatch.await();
    synchronizedList.add("All tasks are ready");
    startCountDownLatch.countDown();
    endCountDownLatch.await();
    synchronizedList.add("All tasks completed");
    log.info("Output: {}", synchronizedList);

    // 有时，我们可能会遇到一个情况，即在 CountdownLatch 倒计时之前，Workers 已经终止了错误。
    // 这可能导致它永远不会达到零并且 await() 永远不会终止。
    // 为了解决这个问题，我们可以引入超时机制。
    // boolean completed = countDownLatch.await(3L, TimeUnit.SECONDS);
    // 测试最终会超时，await() 将返回 false
  }
}
