package com.lzf.local.d240919.after;

import com.lzf.local.d240919.common.AlertRule;
import com.lzf.local.d240919.common.Notification;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author leizefeng
 */
@AllArgsConstructor
@NoArgsConstructor
public abstract class AlertHandler {

  protected AlertRule rule;

  protected Notification notification;

  public abstract void check(AlertCheckInfo alertCheckInfo);
}
