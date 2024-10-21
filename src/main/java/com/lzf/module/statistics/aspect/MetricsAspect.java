package com.lzf.module.statistics.aspect;

import com.lzf.module.statistics.demo.Metrics;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author leizefeng
 */
@Component
@Aspect
@Slf4j
public class MetricsAspect {

  @Autowired
  private Metrics metrics;

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
    metrics.recordResponseTime(apiName, end - start);

    return proceed;
  }
}
