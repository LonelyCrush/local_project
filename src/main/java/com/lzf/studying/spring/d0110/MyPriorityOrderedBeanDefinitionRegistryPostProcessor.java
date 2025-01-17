package com.lzf.studying.spring.d0110;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.PriorityOrdered;

/**
 * @author leizefeng
 */
@Slf4j
public class MyPriorityOrderedBeanDefinitionRegistryPostProcessor implements
    BeanDefinitionRegistryPostProcessor, PriorityOrdered {

  public MyPriorityOrderedBeanDefinitionRegistryPostProcessor() {
    log.info("MyPriorityOrderedBeanDefinitionRegistryPostProcessor init");
  }

  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
      throws BeansException {
    log.info(
        "MyPriorityOrderedBeanDefinitionRegistryPostProcessor postProcessBeanDefinitionRegistry");
  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
      throws BeansException {
    log.info("MyPriorityOrderedBeanDefinitionRegistryPostProcessor postProcessBeanFactory");
  }

  // 排序的值，越小越前面
  // 默认的ConfigurationClassPostProcessor在最后
  @Override
  public int getOrder() {
    return 0;
  }
}
