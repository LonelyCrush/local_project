package com.lzf.local.d2410.d241025.controller;

import com.lzf.local.d2410.d241025.service.IThreadService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leizefeng
 */
@RestController
@RequestMapping("/thread")
public class ThreadController {

  @Resource
  private IThreadService threadService;

  @GetMapping("/test")
  public String test() {
    threadService.test();
    return "Hello Statistics";
  }
}
