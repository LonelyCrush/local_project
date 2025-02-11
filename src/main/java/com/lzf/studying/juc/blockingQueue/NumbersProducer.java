package com.lzf.studying.juc.blockingQueue;

import com.lzf.studying.juc.util.SleepUtil;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author leizefeng
 */
@Slf4j
@AllArgsConstructor
public class NumbersProducer implements Runnable {

    private BlockingQueue<Integer> queue;

    @Override
    public void run() {
      try {
        generateNumbers();
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
    }

    private void generateNumbers() throws InterruptedException {
      int randomInt = ThreadLocalRandom.current().nextInt(10);
      queue.put(randomInt);
      log.info("Produced number: " + randomInt);
    }
}
