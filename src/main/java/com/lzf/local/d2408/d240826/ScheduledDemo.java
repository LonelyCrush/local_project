package com.lzf.local.d2408.d240826;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author leizefeng
 */
@Component
@EnableAsync
@Slf4j
public class ScheduledDemo {

  private static final DateTimeFormatter PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  /**
   * 异步处理不受方法内的时间影响，并行执行
   * 如果没有显式地配置线程池，Spring Boot将使用默认的SimpleAsyncTaskExecutor实现。
   * @see SimpleAsyncTaskExecutor
   */
  @Async
  @Scheduled(fixedRate = 5000)
  public void testScheduleTask() {
    try {
      Thread.sleep(1000);
      log.info("SpringBoot的定时任务1 "
          + Thread.currentThread().getName() + " " + LocalDateTime.now().format(PATTERN));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Async
  @Scheduled(fixedRate = 5000)
  public void testScheduleTask1() {
    try {
      Thread.sleep(2000);
      log.info("SpringBoot的定时任务2 "
          + Thread.currentThread().getName() + " " +  LocalDateTime.now().format(PATTERN));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

//  /**
//   * 使用自定义的线程池（Spring提供的ThreadPoolTaskExecutor）（也可以通过重写方法实现）
//   */
//  @Async("myThreadPoolTaskExecutor")
//  @Scheduled(fixedRate = 8000)
//  public void testScheduleTask1() {
//    try {
//      Thread.sleep(4000);
//      log.info(
//          "SpringBoot的定时任务2 " + Thread.currentThread().getName() + " " + sdf.format(new Date()));
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
//  }
}
