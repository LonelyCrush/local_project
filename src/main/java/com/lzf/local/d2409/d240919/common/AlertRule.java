package com.lzf.local.d2409.d240919.common;

/**
 * @author leizefeng
 */
public class AlertRule {

  public AlertRule getMatchedRule(String api) {
    System.out.println(api);
    return new AlertRule();
  }

  public long getMaxTps() {
    return 0;
  }

  public long getMaxErrorCount() {
    return 0;
  }

  public long getMaxTimeoutTps() {
    return 0;
  }
}
