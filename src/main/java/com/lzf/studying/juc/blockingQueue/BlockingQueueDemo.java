package com.lzf.studying.juc.blockingQueue;

import com.lzf.studying.juc.util.SleepUtil;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import lombok.extern.slf4j.Slf4j;

/**
 * @author leizefeng
 */
@Slf4j
public class BlockingQueueDemo {

  public static void main(String[] args) {
    int BOUND = 10;
    int N_PRODUCERS = 4;
    // 获取当前系统可用的处理器核心数
    int N_CONSUMERS = Runtime.getRuntime().availableProcessors();
    log.info("N_PRODUCERS: {}, N_CONSUMERS: {}", N_PRODUCERS, N_CONSUMERS);
    BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(BOUND);

    // 启动消费者线程
    for (int i = 0; i < N_CONSUMERS; i++) {
      new Thread(new NumbersConsumer(queue)).start();
    }

    // 启动生产者线程
    for (int i = 0; i < N_PRODUCERS; i++) {
      new Thread(new NumbersProducer(queue)).start();
    }
    SleepUtil.sleep();
    log.info("-----------------");
    for (int i = 0; i < N_PRODUCERS; i++) {
      new Thread(new NumbersProducer(queue)).start();
    }
    SleepUtil.sleep();
    log.info("-----------------");
    for (int i = 0; i < N_PRODUCERS; i++) {
      new Thread(new NumbersProducer(queue)).start();
    }
  }
}
