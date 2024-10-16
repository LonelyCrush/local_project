package com.lzf.local.d2409.d240919.myafter.impl;

import com.lzf.local.d2409.d240919.common.AlertRule;
import com.lzf.local.d2409.d240919.common.Notification;
import com.lzf.local.d2409.d240919.common.NotificationEmergencyLevel;
import com.lzf.local.d2409.d240919.myafter.AlertType;
import com.lzf.local.d2409.d240919.myafter.CheckInfo;

/**
 * @author leizefeng
 */
public class AlertTypeTimeoutImpl extends AlertType {

  public AlertTypeTimeoutImpl(AlertRule rule,
      Notification notification) {
    super(rule, notification);
  }

  @Override
  public void check(CheckInfo checkInfo) {
    long timeoutTps = checkInfo.getTimeoutCount() / checkInfo.getDurationOfSeconds();
    if (timeoutTps > rule.getMatchedRule(checkInfo.getApi()).getMaxTimeoutTps()) {
      notification.notify(NotificationEmergencyLevel.URGENCY, "...");
    }
  }
}
