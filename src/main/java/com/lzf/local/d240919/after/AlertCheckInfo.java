package com.lzf.local.d240919.after;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author leizefeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertCheckInfo {

  String api;

  long requestCount;

  long errorCount;

  long timeoutCount;

  long durationOfSeconds;
}
