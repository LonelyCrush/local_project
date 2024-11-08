package com.lzf.local.d2411.d241105;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author leizefeng
 */
//@Component
public class InitTest implements InitializingBean, ApplicationContextAware {

  private ApplicationContext context;

  @Override
  public void afterPropertiesSet() throws Exception {
    BeanTwo beanTwo1 = context.getBean(BeanTwo.class);
    BeanTwo beanTwo2 = context.getBean(BeanTwo.class);
    BeanOne beanOne1 = context.getBean(BeanOne.class);

    beanTwo1.doSomething();
    beanTwo2.doSomething();
    beanOne1.doSomething();
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    context = applicationContext;
  }
}
