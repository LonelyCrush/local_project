package com.lzf.modules.limit.algorithm;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Stopwatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author leizefeng
 */
public class LimitAlgorithm {

  private static final long TRY_LOCK_TIMEOUT = 200L;  // 200ms.

  private Stopwatch stopwatch;

  private final AtomicInteger currentCount = new AtomicInteger(0);

  private final int limit;

  private final Lock lock = new ReentrantLock();

  public LimitAlgorithm(int limit) {
    this.limit = limit;
  }

  @VisibleForTesting
  protected LimitAlgorithm(int limit, Stopwatch stopwatch) {
    this.limit = limit;
    this.stopwatch = stopwatch;
  }

  public boolean tryAcquire() {
    int updatedCount = currentCount.incrementAndGet();
    if (updatedCount <= limit) {
      return true;
    }

    try {
      if (lock.tryLock(TRY_LOCK_TIMEOUT, TimeUnit.MILLISECONDS)) {
        try {
          if (stopwatch.elapsed(TimeUnit.MILLISECONDS) > TimeUnit.SECONDS.toMillis(1)) {
            currentCount.set(0);
            stopwatch.reset();
          }
          updatedCount = currentCount.incrementAndGet();
          return updatedCount <= limit;
        } finally {
          lock.unlock();
        }
      } else {
        throw new RuntimeException("tryAcquire() wait lock too long:" + TRY_LOCK_TIMEOUT + "ms");
      }
    } catch (InterruptedException e) {
      throw new RuntimeException("tryAcquire() is interrupted by lock-time-out.", e);
    }
  }
}
