package com.lzf.module.designpattern.behavior.observer.impl;

import com.lzf.module.designpattern.behavior.observer.Observer;
import org.springframework.stereotype.Component;

/**
 * @author leizefeng
 */
@Component
public class ConcreteObserverOne implements Observer {

  @Override
  public void update(String message) {
    System.out.println(this.getClass().getSimpleName() + " receive message : " + message);
  }
}
