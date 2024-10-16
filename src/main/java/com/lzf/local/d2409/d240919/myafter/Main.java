package com.lzf.local.d2409.d240919.myafter;

/**
 * @author leizefeng
 */
public class Main {

  public static void main(String[] args) {
    CheckInfo checkInfo = new CheckInfo(
        ".../check",
        1L,
        1L,
        1L,
        1L
    );
    Alert alert = new Alert();
    alert.check(checkInfo);
  }
}
