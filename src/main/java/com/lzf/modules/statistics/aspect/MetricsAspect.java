package com.lzf.modules.statistics.aspect;

import com.lzf.modules.statistics.demo.Metrics;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @author leizefeng
 */
@Component
@Aspect
@Slf4j
public class MetricsAspect {

  @Resource
  private Metrics metrics;

  @Resource(name = "commonThreadPoolTaskExecutor")
  private ThreadPoolTaskExecutor threadPoolTaskExecutor;

  // 对注解生效
  @Pointcut("@annotation(com.lzf.module.statistics.annotation.EnableMetrics)")
  public void metricsAspect() {}

  @Around("metricsAspect()")
  public Object aroundMetrics(ProceedingJoinPoint joinPoint) throws Throwable {
    String className = joinPoint.getTarget().getClass().getName();
    String apiName = joinPoint.getSignature().getName();

    long start = System.currentTimeMillis();
    log.debug("Starting metrics-[{}]-[{}]", className, apiName);

    Object proceed = joinPoint.proceed();

    long end = System.currentTimeMillis();
    log.debug("Ending metrics-[{}]-[{}]-[cost {} milliseconds]",
        className, apiName, end - start);
    threadPoolTaskExecutor.submit(() ->
        metrics.recordResponseTime(apiName, end - start));

    return proceed;
  }
}
