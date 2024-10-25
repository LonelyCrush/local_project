package com.lzf.local.d2410.d241025.config;

import java.util.concurrent.ThreadPoolExecutor;
import javax.annotation.Resource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author leizefeng
 */
@Configuration
@ConfigurationProperties(prefix = "thread-pool")
@Data
public class CommonExecutorConfig {

  private Integer corePoolSize;

  private Integer maxPoolSize;

  private Integer keepAliveSeconds;

  private Integer queueCapacity;

  private String threadNamePrefix;

  @Resource
  private CommonDecorator commonDecorator;

  @Bean("commonExecutor")
  public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(corePoolSize);
    executor.setMaxPoolSize(maxPoolSize);
    executor.setKeepAliveSeconds(keepAliveSeconds);
    executor.setQueueCapacity(queueCapacity);
    executor.setThreadNamePrefix(threadNamePrefix);
    executor.setTaskDecorator(commonDecorator);
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    executor.setWaitForTasksToCompleteOnShutdown(true);
    return executor;
  }
}
