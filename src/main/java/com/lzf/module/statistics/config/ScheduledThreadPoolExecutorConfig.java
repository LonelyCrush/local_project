package com.lzf.module.statistics.config;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author leizefeng
 */
@Configuration
@ConfigurationProperties(prefix = "scheduled-thread-pool")
@Data
public class ScheduledThreadPoolExecutorConfig {

  private Integer corePoolSize;

  @Bean("scheduledThreadPoolExecutor")
  public ScheduledThreadPoolExecutor scheduledThreadPoolExecutor() {
    return new ScheduledThreadPoolExecutor(corePoolSize);
  }
}
