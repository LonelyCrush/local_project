package com.lzf.local.d2409.d240919.myafter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author leizefeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckInfo {

  private String api;

  private long requestCount;

  private long errorCount;

  private long timeoutCount;

  private long durationOfSeconds;
}
