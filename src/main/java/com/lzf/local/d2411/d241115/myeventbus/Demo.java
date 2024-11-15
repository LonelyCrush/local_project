package com.lzf.local.d2411.d241115.myeventbus;

import com.lzf.local.d2411.d241115.eventbus.event.CustomEvent;
import com.lzf.local.d2411.d241115.eventbus.subscribe.impl.SubscribeOne;
import java.util.concurrent.Executors;

/**
 * @author leizefeng
 */
public class Demo {

  public static void main(String[] args) {
    MyEventBus myEventBus = new MyEventBus();
    MyAsyncEventBus myAsyncEventBus = new MyAsyncEventBus(Executors.newFixedThreadPool(2));
    SubscribeOne subscribeOne = new SubscribeOne();
    CustomEvent customEvent = new CustomEvent(22);
    myEventBus.register(subscribeOne);
    myAsyncEventBus.register(subscribeOne);
    myEventBus.post(customEvent);
    myAsyncEventBus.post(customEvent);
    System.out.println("ok");
  }
}
