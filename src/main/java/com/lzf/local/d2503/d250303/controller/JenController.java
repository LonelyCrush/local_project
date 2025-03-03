package com.lzf.local.d2503.d250303.controller;

import com.lzf.local.d2503.d250303.service.JenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leizefeng
 */
@RestController
@RequestMapping("/Jen")
public class JenController {

  @Autowired
  private JenService jenService;

  @GetMapping("/hello")
  public String hello() {
    return jenService.hello();
  }
}
