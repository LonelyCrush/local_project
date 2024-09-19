package com.lzf.local.d240919.myafter.impl;

import com.lzf.local.d240919.common.AlertRule;
import com.lzf.local.d240919.common.Notification;
import com.lzf.local.d240919.common.NotificationEmergencyLevel;
import com.lzf.local.d240919.myafter.AlertType;
import com.lzf.local.d240919.myafter.CheckInfo;

/**
 * @author leizefeng
 */
public class AlertTypeErrorImpl extends AlertType {

  public AlertTypeErrorImpl(AlertRule rule,
      Notification notification) {
    super(rule, notification);
  }

  @Override
  public void check(CheckInfo checkInfo) {
    if (checkInfo.getErrorCount() > rule.getMatchedRule(checkInfo.getApi()).getMaxErrorCount()) {
      notification.notify(NotificationEmergencyLevel.SEVERE, "...");
    }
  }
}
