package com.lzf.local.d2410.d241025.service.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.lzf.local.d2410.d241025.config.AsyncUtil;
import com.lzf.local.d2410.d241025.entity.Student;
import com.lzf.local.d2410.d241025.entity.Teacher;
import com.lzf.local.d2410.d241025.service.IThreadService;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * @author leizefeng
 */
@Service
@Slf4j
public class IThreadServiceImpl implements IThreadService {

  @Resource(name = "commonExecutor")
  private ThreadPoolTaskExecutor executor;

  @Override
  public void test() {
    Teacher teacher = new Teacher().setName("teacher1");
    Student student = new Student().setName("student1").setTeacher(teacher);
    Map<String, Object> map = new HashMap<>();
    map.put("teacher", teacher);
    map.put("student", student);

    MDC.put("traceId", UUID.randomUUID().toString());
    log.info("fatherTask");
    AsyncUtil.setAsyncMap(map);
    CompletableFuture.runAsync(this::sonTask, executor);

    MDC.put("traceId", UUID.randomUUID().toString());
    teacher.setName("teacher2");
    student.setName("student2");
    log.info("teacher: {}", teacher);
    log.info("student: {}", student);
  }

  private void sonTask() {
    try {
      Thread.sleep(1000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    log.info("sonTask");

    Map<String, Object> asyncMap = AsyncUtil.getAsyncMap();
    Teacher teacher = (Teacher) asyncMap.get("teacher");
    Student student = (Student) asyncMap.get("student");
    log.info("teacher: {}", teacher);
    log.info("student: {}", student);
  }
}
