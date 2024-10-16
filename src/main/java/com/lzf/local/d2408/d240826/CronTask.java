package com.lzf.local.d2408.d240826;

import com.lzf.d240819.entity.Student;
import com.lzf.d240819.service.StudentService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author leizefeng
 */
@Component
public class CronTask {

  @Resource
  private StudentService studentService;

  /**
   * 定时任务，需要在主类添加注解 @EnableScheduling
   */
//  @Scheduled(cron = "0/2 * * ? * ?")
//  public void doTask() {
//    System.out.println("CronTask doTask...");
//  }

  /**
   * 缓存查询结果，该缓存需要Spring管理的对象使用才能生效，也就是说要注入CronTask
   * @return 学生信息list
   */
  @Cacheable(value = "studentService", key = "#listAll")
  public List<Student> listAll() {
    return studentService.listAll();
  }

  /**
   * 五分钟清除一次缓存，清除缓存之后下一次调用时会刷新缓存
   */
  @Scheduled(fixedDelay = 1000 * 60 * 5)
  public void clearListAllCache() {
    SimpleCacheManager cacheManager = new SimpleCacheManager();
    Cache studentServiceCache = cacheManager.getCache("studentService");
    if (studentServiceCache != null) {
      studentServiceCache.clear();
    }
  }
}
