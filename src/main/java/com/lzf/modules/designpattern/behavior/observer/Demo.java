package com.lzf.modules.designpattern.behavior.observer;

import com.lzf.modules.designpattern.behavior.observer.impl.ConcreteObserverOne;
import com.lzf.modules.designpattern.behavior.observer.impl.ConcreteObserverTwo;
import com.lzf.modules.designpattern.behavior.observer.impl.ConcreteSubject;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author leizefeng
 */
@Component
public class Demo {

  @Resource
  private Subject subject;

  @Resource(type = ConcreteObserverOne.class)
  private Observer observerOne;

  public void update() {
    subject.notifyObservers("update");
    subject.removeObserver(observerOne);
    subject.notifyObservers("update again");
  }

  public static void main(String[] args) {
    Subject subject = new ConcreteSubject();
    Observer observerOne = new ConcreteObserverOne();
    Observer observerTwo = new ConcreteObserverTwo();

    subject.registerObserver(observerOne);
    subject.registerObserver(observerTwo);
    subject.notifyObservers("message 1");

    System.out.println();

    subject.removeObserver(observerOne);
    subject.notifyObservers("message 2");
  }
}
