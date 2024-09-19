package com.lzf.local.d240919.after.impl;

import com.lzf.local.d240919.after.AlertCheckInfo;
import com.lzf.local.d240919.after.AlertHandler;
import com.lzf.local.d240919.common.AlertRule;
import com.lzf.local.d240919.common.Notification;
import com.lzf.local.d240919.common.NotificationEmergencyLevel;

/**
 * @author leizefeng
 */
public class AlertHandlerTimeOut extends AlertHandler {

  public AlertHandlerTimeOut(AlertRule rule, Notification notification) {
    super(rule, notification);
  }

  @Override
  public void check(AlertCheckInfo alertCheckInfo) {
    long timeoutTps = alertCheckInfo.getTimeoutCount() / alertCheckInfo.getDurationOfSeconds();
    if (timeoutTps > rule.getMatchedRule(alertCheckInfo.getApi()).getMaxTimeoutTps()) {
      notification.notify(NotificationEmergencyLevel.URGENCY, "...");
    }
  }
}
