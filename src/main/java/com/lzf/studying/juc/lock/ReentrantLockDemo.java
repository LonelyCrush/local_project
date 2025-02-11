package com.lzf.studying.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import lombok.extern.slf4j.Slf4j;

/**
 * @author leizefeng
 */
@Slf4j
public class ReentrantLockDemo {

  public static void main(String[] args) throws InterruptedException {
    ReentrantLockDemo demo = new ReentrantLockDemo();

    demo.testLock();
    demo.testTryLock();
  }

  private void testLock() {
    ReentrantLock lock = new ReentrantLock();

    int i = 0;
    lock.lock();
    try {
      log.info("获取锁成功, {}", ++i);
    } finally {
      lock.unlock();
    }
  }

  private void testTryLock() throws InterruptedException {
    ReentrantLock lock = new ReentrantLock();

    int i = 0;
    if (lock.tryLock(1, TimeUnit.SECONDS)) {
      try {
        log.info("获取锁成功, {}", ++i);
      } finally {
        lock.unlock();
      }
    } else {
      log.info("获取锁失败");
    }
  }
}
