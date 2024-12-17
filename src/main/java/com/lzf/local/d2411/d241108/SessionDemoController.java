package com.lzf.local.d2411.d241108;

import com.lzf.local.d2411.d241108.mapper.SpringSessionMapper;
import com.lzf.modules.myproject.util.WebUtils;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leizefeng
 */
@RestController
@RequestMapping("/session")
public class SessionDemoController {

  @Resource
  private SpringSessionMapper springSessionMapper;

  @PostMapping("/get")
  public void get() {
    Long count = springSessionMapper.selectCount(null);
    System.out.println(count);
  }

  @PostMapping("/set")
  public void set() {
    HttpServletRequest request = WebUtils.getHttpRequest();
    HttpSession session = request.getSession();
    System.out.println(session.getId());
    session.setAttribute("id", session.getId());
    System.out.println(session.getAttribute("id"));
  }

  @PostMapping("/invalidate")
  public void invalidate() {
    HttpServletRequest request = WebUtils.getHttpRequest();
    HttpSession session = request.getSession();
    session.invalidate();
  }
}
