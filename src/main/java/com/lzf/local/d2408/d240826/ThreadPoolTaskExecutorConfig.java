package com.lzf.local.d2408.d240826;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author leizefeng
 */
@Configuration
@EnableAsync
public class ThreadPoolTaskExecutorConfig implements AsyncConfigurer {

  private Executor initExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    // 最大线程数
    executor.setMaxPoolSize(4);
    // 核心线程数
    executor.setCorePoolSize(2);
    // 任务队列的大小
    executor.setQueueCapacity(10);
    // 线程前缀名
    executor.setThreadNamePrefix("god-jiang-");
    // 线程存活时间
    executor.setKeepAliveSeconds(30);

    /*
     * 拒绝处理策略
     * CallerRunsPolicy()：交由调用方线程运行，比如 main 线程。
     * AbortPolicy()：直接抛出异常。
     * DiscardPolicy()：直接丢弃。
     * DiscardOldestPolicy()：丢弃队列中最老的任务。
     */
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
    // 线程初始化
    executor.initialize();
    return executor;
  }

//  @Bean
//  public Executor myThreadPoolTaskExecutor() {
//    return initExecutor();
//  }

  @Override
  public Executor getAsyncExecutor() {
    return initExecutor();
  }
}
