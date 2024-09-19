package com.lzf.local.d240919.before;

import com.lzf.local.d240919.common.AlertRule;
import com.lzf.local.d240919.common.Notification;

/**
 * @author leizefeng
 */
public class Main {

  public static void main(String[] args) {
    AlertChange alertChange = new AlertChange(new AlertRule(), new Notification());
    alertChange.check(
        "api",
        1L,
        1L,
        1L,
        1L
    );
  }
}
