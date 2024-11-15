package com.lzf.local.d2411.d241115.myeventbus;

import java.util.concurrent.Executor;

/**
 * @author leizefeng
 */
public class MyAsyncEventBus extends MyEventBus{

  public MyAsyncEventBus(Executor executor) {
    super(executor);
  }
}
