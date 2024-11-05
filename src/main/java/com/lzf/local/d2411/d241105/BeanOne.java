package com.lzf.local.d2411.d241105;

import javax.annotation.PostConstruct;

/**
 * @author leizefeng
 */
public class BeanOne {

//  @PostConstruct
  public void init() {
    System.out.println("BeanOne init");
  }

  public void doSomething() {
    System.out.println("BeanOne doSomething");
  }
}
