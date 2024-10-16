package com.lzf.local.d2409.d240919.myafter.impl;

import com.lzf.local.d2409.d240919.common.AlertRule;
import com.lzf.local.d2409.d240919.common.Notification;
import com.lzf.local.d2409.d240919.common.NotificationEmergencyLevel;
import com.lzf.local.d2409.d240919.myafter.AlertType;
import com.lzf.local.d2409.d240919.myafter.CheckInfo;

/**
 * @author leizefeng
 */
public class AlertTypeTpsImpl extends AlertType {

  public AlertTypeTpsImpl(AlertRule rule,
      Notification notification) {
    super(rule, notification);
  }

  @Override
  public void check(CheckInfo checkInfo) {
    long tps = checkInfo.getRequestCount() / checkInfo.getDurationOfSeconds();
    if (tps > rule.getMatchedRule(checkInfo.getApi()).getMaxTps()) {
      notification.notify(NotificationEmergencyLevel.URGENCY, "...");
    }
  }
}
