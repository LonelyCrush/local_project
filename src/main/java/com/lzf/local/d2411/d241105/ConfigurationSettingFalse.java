package com.lzf.local.d2411.d241105;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author leizefeng
 *
 * 默认 proxyBeanMethods = true 的情况下，两个 Bean 都初始化一次，默认被 CGLIB 进行代理了
 * 在 new BeanTwo(beanOne()) 的 beanOne() 中走了 Bean 的生命周期
 * 所以在测试类当中，beanTwo1 和 beanTwo2 是同一个 Bean
 * 且其中的 BeanOne 与 beanOne1 也都是同一个 Bean
 *
 * proxyBeanMethods = false 的情况下，@Bean 标识的方法调用就是普通的方法调用，不会被代理
 * new BeanTwo(beanOne()) 的 beanOne() 中不走 Bean 的生命周期
 * 所以在测试类当中，beanTwo1 和 beanTwo2 是同一个 Bean
 * 但其中的 BeanOne 与 beanOne1 不是同一个 Bean
 *
 */
//@Configuration(proxyBeanMethods = false)
public class ConfigurationSettingFalse {

  @Bean
  public BeanOne beanOne() {
    return new BeanOne();
  }

  @Bean
  public BeanTwo beanTwo() {
    return new BeanTwo(beanOne());
  }
}
