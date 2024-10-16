package com.lzf.local.d2409.d240919.after;

import com.lzf.local.d2409.d240919.after.impl.AlertHandlerError;
import com.lzf.local.d2409.d240919.after.impl.AlertHandlerTimeOut;
import com.lzf.local.d2409.d240919.after.impl.AlertHandlerTps;
import com.lzf.local.d2409.d240919.common.AlertRule;
import com.lzf.local.d2409.d240919.common.Notification;
import lombok.Getter;

/**
 * @author leizefeng
 */
@Getter
public class AlertContext {

  private AlertRule alertRule;

  private Notification notification;

  private Alert alert;

  public void initializeBeans() {
    alertRule = new AlertRule();
    notification = new Notification();
    alert = new Alert();
    alert.addAlertHandler(new AlertHandlerTps(alertRule, notification));
    alert.addAlertHandler(new AlertHandlerError(alertRule, notification));
    alert.addAlertHandler(new AlertHandlerTimeOut(alertRule, notification));
  }

  private static final AlertContext instance = new AlertContext();

  private AlertContext() {
    initializeBeans();
  }

  public static AlertContext getInstance() {
    return instance;
  }
}
