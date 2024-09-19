package com.lzf.local.d240919.myafter;

import com.lzf.local.d240919.common.AlertRule;
import com.lzf.local.d240919.common.Notification;

/**
 * @author leizefeng
 */
public abstract class AlertType {

  protected AlertRule rule;

  protected Notification notification;

  public AlertType(AlertRule rule, Notification notification) {
    this.rule = rule;
    this.notification = notification;
  }

  public abstract void check(CheckInfo checkInfo);
}
