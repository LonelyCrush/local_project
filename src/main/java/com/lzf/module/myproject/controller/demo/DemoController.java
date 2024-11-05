package com.lzf.module.myproject.controller.demo;

import com.lzf.module.myproject.service.demo.DemoService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leizefeng
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

  @Resource
  private DemoService demoService;

  @GetMapping
  public String demo() {
    demoService.demo();
    return "demo";
  }
}
