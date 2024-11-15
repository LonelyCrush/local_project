package com.lzf.local.d2411.d241115.eventbus.subscribe.impl;

import com.google.common.eventbus.Subscribe;
import com.lzf.local.d2411.d241115.eventbus.event.CustomEvent;
import com.lzf.local.d2411.d241115.eventbus.subscribe.CustomSubscriber;
import java.time.Instant;

/**
 * @author leizefeng
 */
public class SubscribeTwo implements CustomSubscriber {

  @Subscribe
  public void test1(CustomEvent event) {
    System.out.println(Instant.now() + "监听者2-->回调1，收到事件：" + event.getData()
        + "，线程号为：" + Thread.currentThread().getName());
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Subscribe
  public void test2(CustomEvent event) {
    System.out.println(Instant.now() + "监听者2-->回调2，收到事件：" + event.getData()
        + "，线程号为：" + Thread.currentThread().getName());
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
