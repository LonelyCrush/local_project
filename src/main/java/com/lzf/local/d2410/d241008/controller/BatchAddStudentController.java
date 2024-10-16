package com.lzf.local.d2410.d241008.controller;

import com.lzf.local.d2410.d241008.service.BatchAddStudentService;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leizefeng
 */
@RestController
@RequestMapping("/student")
public class BatchAddStudentController {

  @Resource
  private BatchAddStudentService batchAddStudentService;

  @PostMapping("/batchAdd")
  public void batchAdd() {
    batchAddStudentService.batchAdd();
  }
}
