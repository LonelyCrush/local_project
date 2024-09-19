package com.lzf.local.d240919.after;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leizefeng
 */
public class Alert {

  private final List<AlertHandler> alertHandlerList = new ArrayList<>();

  public void addAlertHandler(AlertHandler alertHandler) {
    alertHandlerList.add(alertHandler);
  }

  public void check(AlertCheckInfo alertCheckInfo) {
    for (AlertHandler item : alertHandlerList) {
      item.check(alertCheckInfo);
    }
  }
}
