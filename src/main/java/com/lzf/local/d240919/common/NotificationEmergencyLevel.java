package com.lzf.local.d240919.common;

/**
 * @author leizefeng
 */
public enum NotificationEmergencyLevel {
  SEVERE("P0", "Severe"), URGENCY("P1", "Urgency");

  private final String code;

  private final String msg;

  NotificationEmergencyLevel(String code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public String getCode() {
    return code;
  }

  public String getMsg() {
    return msg;
  }
}
