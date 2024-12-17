package com.lzf.modules.designpattern.behavior.observer.impl;

import com.lzf.modules.designpattern.behavior.observer.Observer;
import com.lzf.modules.designpattern.behavior.observer.Subject;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author leizefeng
 */
@Component
public class ConcreteSubject implements Subject {

  // 默认注入所有观察者
  @Resource
  private List<Observer> observerList;

  @Override
  public void registerObserver(Observer observer) {
    observerList.add(observer);
  }

  @Override
  public void removeObserver(Observer observer) {
    observerList.remove(observer);
  }

  @Override
  public void notifyObservers(String message) {
    for (Observer observer : observerList) {
      observer.update(message);
    }
  }
}
