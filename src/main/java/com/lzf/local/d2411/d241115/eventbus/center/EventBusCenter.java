package com.lzf.local.d2411.d241115.eventbus.center;

import com.google.common.eventbus.EventBus;
import com.lzf.local.d2411.d241115.eventbus.event.CustomEvent;
import com.lzf.local.d2411.d241115.eventbus.subscribe.CustomSubscriber;

/**
 * @author leizefeng
 */
public class EventBusCenter {

  private static volatile EventBus eventBus;

  private static EventBus getEventBus() {
    if (eventBus == null) {
      synchronized (EventBus.class) {
        if (eventBus == null) {
          eventBus = new EventBus();
        }
      }
    }
    return eventBus;
  }

  public static void register(CustomSubscriber subscriber) {
    getEventBus().register(subscriber);
  }

  public static void post(CustomEvent event) {
    getEventBus().post(event);
  }
}
