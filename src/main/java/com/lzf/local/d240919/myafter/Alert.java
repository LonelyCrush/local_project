package com.lzf.local.d240919.myafter;

import com.lzf.local.d240919.common.AlertRule;
import com.lzf.local.d240919.common.Notification;
import com.lzf.local.d240919.myafter.impl.AlertTypeErrorImpl;
import com.lzf.local.d240919.myafter.impl.AlertTypeTimeoutImpl;
import com.lzf.local.d240919.myafter.impl.AlertTypeTpsImpl;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leizefeng
 */
public class Alert {

  private static final List<AlertType> alertTypeList = new ArrayList<>();

  public void check(CheckInfo checkInfo) {
    for (AlertType item : alertTypeList) {
      item.check(checkInfo);
    }
  }

  private static void addAlertType(AlertType alertType) {
    alertTypeList.add(alertType);
  }

  static {
    AlertRule alertRule = new AlertRule();
    Notification notification = new Notification();
    addAlertType(new AlertTypeTpsImpl(alertRule, notification));
    addAlertType(new AlertTypeErrorImpl(alertRule, notification));
    addAlertType(new AlertTypeTimeoutImpl(alertRule, notification));
  }
}
