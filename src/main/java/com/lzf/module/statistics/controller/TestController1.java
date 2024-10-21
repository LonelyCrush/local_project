package com.lzf.module.statistics.controller;

import com.lzf.module.statistics.annotation.EnableMetrics;
import com.lzf.module.statistics.demo.Metrics;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leizefeng
 */
@RestController
@RequestMapping("/statistics")
public class TestController1 {

  @Resource
  private Metrics metrics;

  @GetMapping("/test")
  @EnableMetrics
  public String test1() {
    return "Hello Statistics";
  }

  @PostMapping("/test")
  @EnableMetrics
  public String test2() {
    try {
      Thread.sleep((long) (2000 * Math.random()));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "Hello Statistics";
  }

  @PostMapping("/enable")
  public String enableReport() {
    metrics.repeatedReport();
    return "report enabled";
  }

  @PostMapping("/disable")
  public String disableReport() {
    metrics.shutdown();
    return "report disabled";
  }
}
