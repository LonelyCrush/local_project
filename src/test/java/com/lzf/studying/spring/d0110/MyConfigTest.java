package com.lzf.studying.spring.d0110;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author leizefeng
 */
@Slf4j
public class MyConfigTest {

  @Test
  public void test1() {
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext(MyConfig.class);
  }

  @Test
  public void test2() {
    AnnotationConfigApplicationContext applicationContext =
        new AnnotationConfigApplicationContext();
    applicationContext.register(MyConfig.class);
    applicationContext.addBeanFactoryPostProcessor(new MyBeanDefinitionRegistryPostProcessor());
    applicationContext.refresh();
  }

  @Test
  public void test3() {


  }
}
