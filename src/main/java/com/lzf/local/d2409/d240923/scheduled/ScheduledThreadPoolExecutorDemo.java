package com.lzf.local.d2409.d240923.scheduled;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/**
 * @author leizefeng
 */
@Slf4j
public class ScheduledThreadPoolExecutorDemo {

  public static void main(String[] args) {
    // 创建一个 ScheduledThreadPoolExecutor，支持定时任务
    ScheduledThreadPoolExecutor executor
        = new ScheduledThreadPoolExecutor(2, Executors.defaultThreadFactory());

    // 示例：计划一个任务在 5 秒后执行
    executor.schedule(() -> log.info("任务在 5 秒后执行一次"),
        5, TimeUnit.SECONDS);

    // 示例：每隔 3 秒执行一次任务
    executor.scheduleAtFixedRate(() -> log.info("每 3 秒执行一次任务，一开始的时候会执行一次"),
        0, 3, TimeUnit.SECONDS);

    // 记得在程序结束前关闭 executor
    try {
      Thread.sleep(10000); // 主线程睡眠 10 秒，以便让调度的任务执行
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      executor.shutdown();
    }
  }
}

