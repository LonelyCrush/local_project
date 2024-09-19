package com.lzf.local.d240919.common;

/**
 * @author leizefeng
 */
public class Notification {

  public void notify(NotificationEmergencyLevel notificationEmergencyLevel, String s) {
    System.out.println(notificationEmergencyLevel.getCode() +
        ": " + notificationEmergencyLevel.getMsg() +
        ": " + s);
  }
}