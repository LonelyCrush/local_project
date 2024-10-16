package com.lzf.local.d2409.d240919.after.impl;

import com.lzf.local.d2409.d240919.after.AlertCheckInfo;
import com.lzf.local.d2409.d240919.after.AlertHandler;
import com.lzf.local.d2409.d240919.common.AlertRule;
import com.lzf.local.d2409.d240919.common.Notification;
import com.lzf.local.d2409.d240919.common.NotificationEmergencyLevel;

/**
 * @author leizefeng
 */
public class AlertHandlerError extends AlertHandler {

  public AlertHandlerError(AlertRule rule, Notification notification) {
    super(rule, notification);
  }

  @Override
  public void check(AlertCheckInfo alertCheckInfo) {
    if (alertCheckInfo.getErrorCount() >
        rule.getMatchedRule(alertCheckInfo.getApi()).getMaxErrorCount()) {
      notification.notify(NotificationEmergencyLevel.SEVERE, "...");
    }
  }
}
