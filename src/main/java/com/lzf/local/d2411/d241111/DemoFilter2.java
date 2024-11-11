package com.lzf.local.d2411.d241111;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author leizefeng
 */
public class DemoFilter2 implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println("DemoFilter2 init");
    Filter.super.init(filterConfig);
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    System.out.println("DemoFilter2 is running...");
    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {
    System.out.println("DemoFilter2 destroy");
    Filter.super.destroy();
  }
}
