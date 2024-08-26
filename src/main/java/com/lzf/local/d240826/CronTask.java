package com.lzf.local.d240826;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author leizefeng
 */
@Component
public class CronTask {

  /**
   * 定时任务，需要在主类添加注解 @EnableScheduling
   */
  @Scheduled(cron = "0/2 * * ? * ?")
  public void doTask() {
    System.out.println("CronTask doTask...");
  }
}
