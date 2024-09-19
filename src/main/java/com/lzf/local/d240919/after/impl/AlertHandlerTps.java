package com.lzf.local.d240919.after.impl;

import com.lzf.local.d240919.after.AlertCheckInfo;
import com.lzf.local.d240919.after.AlertHandler;
import com.lzf.local.d240919.common.AlertRule;
import com.lzf.local.d240919.common.Notification;
import com.lzf.local.d240919.common.NotificationEmergencyLevel;

public class AlertHandlerTps extends AlertHandler {

  public AlertHandlerTps(AlertRule rule, Notification notification) {
    super(rule, notification);
  }

  @Override
  public void check(AlertCheckInfo alertCheckInfo) {
    long tps = alertCheckInfo.getRequestCount()/ alertCheckInfo.getDurationOfSeconds();
    if (tps > rule.getMatchedRule(alertCheckInfo.getApi()).getMaxTps()) {
      notification.notify(NotificationEmergencyLevel.URGENCY, "...");
    }
  }
}
