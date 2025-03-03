package com.lzf.local.d2503.d250303.service.impl;

import com.lzf.local.d2503.d250303.service.JenService;
import org.springframework.stereotype.Service;

/**
 * @author leizefeng
 */
@Service
public class JenServiceImpl implements JenService {

  @Override
  public String hello() {
    return "Hello, Jen";
  }
}
