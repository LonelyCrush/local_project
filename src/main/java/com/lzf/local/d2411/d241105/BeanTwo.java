package com.lzf.local.d2411.d241105;

import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author leizefeng
 */
@Data
@AllArgsConstructor
public class BeanTwo {

  private BeanOne beanOne;

  @PostConstruct
  public void init() {
    System.out.println("BeanTwo init");
  }

  public void doSomething() {
    System.out.println("BeanTwo doSomething");
  }
}
