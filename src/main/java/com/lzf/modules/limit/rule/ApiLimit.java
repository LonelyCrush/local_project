package com.lzf.modules.limit.rule;

import lombok.Data;

/**
 * @author leizefeng
 */
@Data
public class ApiLimit {

  private static final int DEFAULT_TIME_UNIT = 1; // 默认为1秒

  private String api;

  private int limit;

  private int unit = DEFAULT_TIME_UNIT;
}
