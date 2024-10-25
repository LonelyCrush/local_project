package com.lzf.local.d2410.d241025.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author leizefeng
 */
@Slf4j
public class Demo {

  private static final ThreadLocal<Map<String, Object>> demoThreadLocal = new ThreadLocal<>();

  private static final Map<String, Object> demoMap = new HashMap<>();

  private static final ThreadPoolTaskExecutor demoExecutor = new ThreadPoolTaskExecutor();

  {
    initExecutor();
  }

  public void initExecutor() {
    demoExecutor.setCorePoolSize(2);
    demoExecutor.setMaxPoolSize(4);
    demoExecutor.setKeepAliveSeconds(10);
    demoExecutor.setQueueCapacity(10);
    demoExecutor.setThreadNamePrefix("demoExecutor-");
    demoExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
    demoExecutor.initialize();
  }

  public static void main(String[] args) {
    Demo demo = new Demo();
    demo.fatherTask();
  }

  private void fatherTask() {
    Teacher teacher = new Teacher().setName("teacher1");
    Student student = new Student().setName("student1").setTeacher(teacher);
    try {
      demoMap.put("teacher", teacher.clone());
      demoMap.put("student", student.clone());
    } catch (CloneNotSupportedException e) {
      e.printStackTrace();
    }

    MDC.put("traceId", UUID.randomUUID().toString());

    // 不使用装饰器
    Map<String, String> map = MDC.getCopyOfContextMap();
    CompletableFuture.runAsync(() -> {
      try {
        MDC.setContextMap(map);
        demoThreadLocal.set(demoMap);
        sonTask();
      } finally {
        MDC.clear();
        demoThreadLocal.remove();
      }
    }, demoExecutor);

    teacher.setName("teacher2");
    student.setName("student2");
    log.info("{}-teacher: {}", Thread.currentThread().getName(), teacher);
    log.info("{}-student: {}", Thread.currentThread().getName(), student);
  }

  private void sonTask() {
    try {
      Thread.sleep(1000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Map<String, Object> map = demoThreadLocal.get();
    Teacher teacher = (Teacher) map.get("teacher");
    Student student = (Student) map.get("student");
    log.info("{}-teacher: {}", Thread.currentThread().getName(), teacher);
    log.info("{}-student: {}", Thread.currentThread().getName(), student);
  }
}
