package com.lzf.modules.myproject.context;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 是一个 写时复制 的上下文存储实现
 * @author leizefeng
 */
public class CopyOnWriteThreadLocalContext implements ServiceContext {

  private static final int READ_OPERATION = 0;

  private static final int WRITE_OPERATION = 1;

  private final ThreadLocal<Map<String, Object>> context = new ThreadLocal<>();

  private final ThreadLocal<Integer> lastOperation = new ThreadLocal<>();

  private final Object lock = new Object();

  private Integer getAndSetLastOperation(int newOperation) {
    Integer oldOperation = lastOperation.get();
    lastOperation.set(newOperation);
    return oldOperation;
  }

  @Override
  public void put(String key, String value) {
    if (key == null) {
      throw new IllegalArgumentException("Key cannot be null");
    }
    Map<String, Object> oldMap = context.get();
    Integer oldOperation = lastOperation.get();
    if (!wasOperationReadOrNull(oldOperation) && oldMap != null) {
      oldMap.put(key, value);
    } else {
      Map<String, Object> newMap = copyOnWrite(oldMap);
      newMap.put(key, value);
    }
  }

  private boolean wasOperationReadOrNull(Integer operation) {
    return operation == null || READ_OPERATION == operation;
  }

  // 写时复制
  private Map<String, Object> copyOnWrite(Map<String, Object> map) {
    Map<String, Object> newMap = Collections.synchronizedMap(new HashMap<>());
    if (map != null) {
      synchronized (lock) {
        newMap.putAll(map);
      }
      context.set(newMap);
    }
    return newMap;
  }

  @Override
  public String get(String key) {
    if (key == null) {
      return null;
    }
    Map<String, Object> oldMap = context.get();
    if (oldMap == null || oldMap .get(key) == null) {
      return null;
    }
    return (String) oldMap.get(key);
  }

  @Override
  public String remove(String key) {
    if (key != null) {
      Map<String, Object> oldMap = context.get();
      if (oldMap != null && oldMap.get(key) != null) {
        Integer oldOperation = lastOperation.get();
        if (wasOperationReadOrNull(oldOperation)) {
          Map<String, Object> newMap = copyOnWrite(oldMap);
          return (String) newMap.remove(key);
        }
      }
    }
    return null;
  }

  @Override
  public void clear() {
    context.remove();
    lastOperation.set(WRITE_OPERATION);
  }

  @Override
  public Map<String, Object> getCopyOfContextMap() {
    Map<String, Object> oldMap = context.get();
    return oldMap == null ? new HashMap<>(0) : new HashMap<>(oldMap);
  }

  @Override
  public void setContextMap(Map<String, Object> data) {
    Map<String, Object> newMap = Collections.synchronizedMap(new HashMap<>());
    newMap.putAll(data);
    context.set(newMap);
    lastOperation.set(WRITE_OPERATION);
  }
}
