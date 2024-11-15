package com.lzf.local.d2411.d241115.eventbus.test;

import com.google.common.eventbus.AsyncEventBus;
import com.lzf.local.d2411.d241115.eventbus.center.AsyncEventBusCenter;
import com.lzf.local.d2411.d241115.eventbus.center.EventBusCenter;
import com.lzf.local.d2411.d241115.eventbus.event.CustomEvent;
import com.lzf.local.d2411.d241115.eventbus.subscribe.CustomSubscriber;
import com.lzf.local.d2411.d241115.eventbus.subscribe.impl.SubscribeOne;
import com.lzf.local.d2411.d241115.eventbus.subscribe.impl.SubscribeTwo;
import java.time.Instant;
import java.util.concurrent.Executors;

/**
 * @author leizefeng
 */
public class Demo {

  public static void main(String[] args) {
    Demo demo = new Demo();
//    demo.testEventBus();
//    System.out.println();
//    demo.testAsyncEvenBus();
    demo.test();
  }

  public void testEventBus() {
    SubscribeOne subscribeOne = new SubscribeOne();
    SubscribeTwo subscribeTwo = new SubscribeTwo();
    CustomEvent customEvent = new CustomEvent(20);
    EventBusCenter.register(subscribeOne);
    EventBusCenter.register(subscribeTwo);
    EventBusCenter.post(customEvent);
    System.out.println(Instant.now() +",主线程执行完毕："+Thread.currentThread().getName());
  }

  public void testAsyncEvenBus() {
    SubscribeOne subscribeOne = new SubscribeOne();
    SubscribeTwo subscribeTwo = new SubscribeTwo();
    CustomEvent customEvent = new CustomEvent(22);
    AsyncEventBusCenter.register(subscribeOne);
    AsyncEventBusCenter.register(subscribeTwo);
    AsyncEventBusCenter.post(customEvent);
    System.out.println(Instant.now() +",主线程执行完毕："+Thread.currentThread().getName());
  }

  public void test() {
    CustomSubscriber subscriber = new SubscribeOne();
    CustomEvent event = new CustomEvent(22);
    AsyncEventBus asyncEventBus = new AsyncEventBus(Executors.newFixedThreadPool(2));

    asyncEventBus.register(subscriber);
    asyncEventBus.post(event); // async thread pool to do
    System.out.println("ok");
  }
}
