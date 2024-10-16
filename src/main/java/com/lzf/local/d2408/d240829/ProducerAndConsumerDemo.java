package com.lzf.local.d2408.d240829;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author leizefeng
 */
public class ProducerAndConsumerDemo {

  // 消费线程池
  private final ThreadPoolExecutor executor = new ThreadPoolExecutor(
      2,
      4,
      10,
      TimeUnit.SECONDS,
      new LinkedBlockingQueue<>(10));

  // 消息队列
  private final LinkedBlockingQueue<String> messageQueue = new LinkedBlockingQueue<>(10);

  public void initConsumer() {
    executor.execute(() -> {
      while (true) {
        synchronized (messageQueue) {
          while (messageQueue.isEmpty()) {
            try {
              messageQueue.wait();
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
          try {
            Thread.sleep(2000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          System.out.println(
              Thread.currentThread().getName() + " consume message: " + messageQueue.poll());
          messageQueue.notifyAll();
        }
      }
    });
  }

  public void sendMessage(String message) {
    executor.execute(() -> {
      synchronized (messageQueue) {
        if (messageQueue.size() >= 10) {
          try {
            messageQueue.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        messageQueue.offer(message);
        messageQueue.notifyAll();
      }
    });
  }

  public static void main(String[] args) {
    ProducerAndConsumerDemo demo = new ProducerAndConsumerDemo();
    demo.initConsumer();
    for (int i = 0; i < 10; i++) {
      demo.sendMessage("message-" + i);
      System.out.println(Thread.currentThread().getName() + " send message: " + "message-" + i);
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
