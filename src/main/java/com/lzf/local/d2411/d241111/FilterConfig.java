package com.lzf.local.d2411.d241111;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author leizefeng
 */
//@Configuration
public class FilterConfig {

//  @Bean
  public FilterRegistrationBean<DemoFilter2> demoFilter2() {
    FilterRegistrationBean<DemoFilter2> registrationBean = new FilterRegistrationBean<>();
    DemoFilter2 filter = new DemoFilter2();
    registrationBean.setFilter(filter);
    List<String> urls = new ArrayList<>();
    urls.add("/*");
    registrationBean.addUrlPatterns(urls.toArray(new String[0]));
    registrationBean.setOrder(3);
    return registrationBean;
  }
}
