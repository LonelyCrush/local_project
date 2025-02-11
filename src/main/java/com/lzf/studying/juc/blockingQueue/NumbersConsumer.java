package com.lzf.studying.juc.blockingQueue;

import com.lzf.studying.juc.util.SleepUtil;
import java.util.concurrent.BlockingQueue;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author leizefeng
 */
@AllArgsConstructor
@Slf4j
public class NumbersConsumer implements Runnable {

  private BlockingQueue<Integer> queue;

  @Override
  public void run() {
    while (true) {
      try {
        Integer take = queue.take();
        SleepUtil.sleep(1500);
        log.info("Consumed number: {}", take);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
