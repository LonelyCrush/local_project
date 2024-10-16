package com.lzf.local.d2409.d240919.before;

import com.lzf.local.d2409.d240919.common.AlertRule;
import com.lzf.local.d2409.d240919.common.Notification;

/**
 * @author leizefeng
 */
public class Main {

  public static void main(String[] args) {
    Alert alert = new Alert(new AlertRule(), new Notification());
    alert.check(
        "api",
        1L,
        1L,
        1L,
        1L
    );
  }
}
