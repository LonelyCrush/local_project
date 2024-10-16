package com.lzf.local.d2410.d241016;

import java.util.UUID;

/**
 * @author leizefeng
 */
public class UniqueKey {

  public static void main(String[] args) {
    String s = UUID.randomUUID().toString().replace("-", "") + System.currentTimeMillis();
    System.out.println(s);
  }
}
