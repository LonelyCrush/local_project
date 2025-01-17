package com.lzf.studying.spring.d0110;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @author leizefeng
 */
@Slf4j
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry)
      throws BeansException {
    log.info("MyBeanDefinitionRegistryPostProcessor postProcessBeanDefinitionRegistry");
//    registry.registerBeanDefinition("myPostProcessor", new AnnotatedGenericBeanDefinition(
//        MyPriorityOrderedBeanDefinitionRegistryPostProcessor.class));
  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
      throws BeansException {
    log.info("MyBeanDefinitionRegistryPostProcessor postProcessBeanFactory");
  }
}
