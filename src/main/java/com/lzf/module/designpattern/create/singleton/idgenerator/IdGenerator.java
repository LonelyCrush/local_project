package com.lzf.module.designpattern.create.singleton.idgenerator;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author leizefeng
 */
public class IdGenerator {

  // AtomicLong是一个Java并发库中提供的一个原子变量类型,
  // 它将一些线程不安全需要加锁的复合操作封装为了线程安全的原子操作，
  // 比如下面会用到的incrementAndGet().
  private final AtomicLong id = new AtomicLong(0);

  private IdGenerator() {}

  // 饿汉式
  private static final IdGenerator instance1 = new IdGenerator();
  public static IdGenerator getInstance1() {
    return instance1;
  }

  // 懒汉式（并发度很低）
  private static IdGenerator instance2;
  public static synchronized IdGenerator getInstance2() {
    if (instance2 == null) {
      instance2 = new IdGenerator();
    }
    return instance2;
  }

  // 双重检测锁
  private static volatile IdGenerator instance3;
  public static IdGenerator getInstance3() {
    if (instance3 == null) {
      synchronized (IdGenerator.class) {
        if (instance3 == null) {
          instance3 = new IdGenerator();
        }
      }
    }
    return instance3;
  }

  // 静态内部类
  private static class SingletonHolder {
    private static final IdGenerator instance4 = new IdGenerator();
  }
  public static IdGenerator getInstance4() {
    return SingletonHolder.instance4;
  }

  // 枚举
  public enum IdGeneratorEnum {
    INSTANCE;
    private final AtomicLong id = new AtomicLong(0);

    public long getId() {
      return id.incrementAndGet();
    }
  }

  public long getId() {
    return id.incrementAndGet();
  }

  public static void main(String[] args) {
    // IdGenerator使用举例
    for (int i = 0; i < 5; i++) {
      System.out.print(IdGenerator.getInstance1().getId() + " ");
      System.out.print(IdGenerator.getInstance2().getId() + " ");
      System.out.print(IdGenerator.getInstance3().getId() + " ");
      System.out.print(IdGenerator.getInstance4().getId() + " ");
      System.out.print(IdGeneratorEnum.INSTANCE.getId() + " ");
      System.out.println();
    }
  }
}
