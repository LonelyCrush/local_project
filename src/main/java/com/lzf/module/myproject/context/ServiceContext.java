package com.lzf.module.myproject.context;

import java.util.Map;

/**
 * @author leizefeng
 */
public interface ServiceContext {

  void put(String key, String value);

  String get(String key);

  String remove(String key);

  void clear();

  Map<String, Object> getCopyOfContextMap();

  void setContextMap(Map<String, Object> data);
}
