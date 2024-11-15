package com.lzf.local.d2411.d241115.myeventbus;

import com.google.common.util.concurrent.MoreExecutors;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author leizefeng
 */
public class MyEventBus {

  private final Executor executor;

  private final MyObserverRegistry registry = new MyObserverRegistry();

  public MyEventBus() {
    this(MoreExecutors.directExecutor());
  }

  protected MyEventBus(Executor executor) {
    this.executor = executor;
  }

  public void register(Object object) {
    registry.register(object);
  }

  public void post(Object event) {
    List<MyObserverAction> observerActions = registry.getMatchedObserverActions(event);
    for (MyObserverAction observerAction : observerActions) {
      executor.execute(() -> observerAction.execute(event));
    }
  }
}
