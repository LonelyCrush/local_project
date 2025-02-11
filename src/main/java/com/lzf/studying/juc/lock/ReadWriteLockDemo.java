package com.lzf.studying.juc.lock;

import com.lzf.studying.juc.util.SleepUtil;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import lombok.extern.slf4j.Slf4j;

/**
 * @author leizefeng
 */
@Slf4j
public class ReadWriteLockDemo {

  public static void main(String[] args) {
    SyncHashMap syncHashMap = new SyncHashMap();
    syncHashMap.put("a", 1);
    log.info(syncHashMap.toString());

    // 获取锁时会阻塞直到锁释放
    for (int i = 0; i < 4; i++) {
      int finalI = i;
      new Thread(() -> {
        syncHashMap.put(Thread.currentThread().getName(), finalI);
      }).start();
    }
    SleepUtil.sleep(2000);
    // 注意此处，由于获取读锁时在阻塞，实际上是过了4秒之后才打印的
    log.info(syncHashMap.toString());
  }

  private static class SyncHashMap {
    Map<String, Object> map = new HashMap<>();
    ReadWriteLock lock = new ReentrantReadWriteLock();
    Lock readLock = lock.readLock();
    Lock writeLock = lock.writeLock();

    public Object get(String key) {
      readLock.lock();
      try {
        return map.get(key);
      } finally {
        readLock.unlock();
      }
    }

    public boolean containsKey(String key) {
      readLock.lock();
      try {
        return map.containsKey(key);
      } finally {
        readLock.unlock();
      }
    }

    public void put(String key, Object value) {
      writeLock.lock();
//      log.info(Thread.currentThread().getName() + " 开始写入: " + LocalDateTime.now());
      SleepUtil.sleep(1000);
      try {
        map.put(key, value);
      } finally {
        writeLock.unlock();
//        log.info(Thread.currentThread().getName() + " 结束写入: " + LocalDateTime.now());
      }
    }

    public void remove(String key) {
      writeLock.lock();
      try {
        map.remove(key);
      } finally {
        writeLock.unlock();
      }
    }

    @Override
    public String toString() {
      readLock.lock();
//      log.info("开始打印: " + LocalDateTime.now());
      try {
        return map.toString();
      } finally {
        readLock.unlock();
      }
    }
  }
}
