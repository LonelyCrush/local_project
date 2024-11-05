package com.lzf.module.myproject.service.demo.impl;

import com.lzf.module.myproject.service.demo.DemoService;
import com.lzf.module.myproject.util.WebUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

/**
 * @author leizefeng
 */
@Service
public class DemoServiceImpl implements DemoService {

  @Override
  public void demo() {
    HttpServletRequest request = WebUtils.getHttpRequest();
    HttpSession session = request.getSession();
    String id = session.getId();
    System.out.println(id);
  }
}
