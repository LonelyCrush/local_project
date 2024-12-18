package com.lzf.modules.designpattern.behavior.observer;

/**
 * @author leizefeng
 */
public interface Subject {

  void registerObserver(Observer observer);

  void removeObserver(Observer observer);

  void notifyObservers(String message);
}
