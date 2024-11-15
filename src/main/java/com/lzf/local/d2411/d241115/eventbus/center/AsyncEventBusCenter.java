package com.lzf.local.d2411.d241115.eventbus.center;

import com.google.common.eventbus.AsyncEventBus;
import com.lzf.local.d2411.d241115.eventbus.event.CustomEvent;
import com.lzf.local.d2411.d241115.eventbus.subscribe.CustomSubscriber;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author leizefeng
 */
public class AsyncEventBusCenter {

  private static volatile AsyncEventBus asyncEventBus;

  private static final Executor executor = Executors.newFixedThreadPool(2);

  public static AsyncEventBus getAsyncEventBus() {
    if (asyncEventBus == null) {
      synchronized (AsyncEventBus.class) {
        if (asyncEventBus == null) {
          asyncEventBus = new AsyncEventBus(executor);
        }
      }
    }
    return asyncEventBus;
  }

  public static void register(CustomSubscriber subscriber) {
    getAsyncEventBus().register(subscriber);
  }

  public static void post(CustomEvent event) {
    getAsyncEventBus().post(event);
  }
}
