package com.lzf.local.d2411.d241111;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author leizefeng
 */
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class DemoFilter0 extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    System.out.println("DemoFilter0 is running...");
    filterChain.doFilter(request, response);
  }
}
