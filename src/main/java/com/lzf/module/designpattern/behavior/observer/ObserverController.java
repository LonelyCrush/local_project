package com.lzf.module.designpattern.behavior.observer;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leizefeng
 */
@RestController
@RequestMapping("/observer")
public class ObserverController {

  @Resource
  private Demo demo;

  @PostMapping("/update")
  public void update() {
    demo.update();

  }
}
