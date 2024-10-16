package com.lzf.local.d2409.d240923.kiss;

import org.springframework.util.StringUtils;

/**
 * 检查输入的字符串ipAddress是否是合法的IP地址
 * @author leizefeng
 */
public class CheckUtils {

  // 第一种实现方式: 使用正则表达式
  private static boolean checkIp1(String ip) {
    if (!StringUtils.hasText(ip)) return false;
    String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
        + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
        + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
        + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
    return ip.matches(regex);
  }

  // 第二种实现方式: 使用现成的工具类
  public boolean checkIp2(String ip) {
    if (!StringUtils.hasText(ip)) return false;
    String[] ipItems = ip.split("\\.");
    if (ipItems.length != 4) return false;
    for (int i = 0; i < ipItems.length; i++) {
      int parseItem;
      try {
        parseItem = Integer.parseInt(ipItems[i]);
      } catch (NumberFormatException e) {
        return false;
      }
      if (i == 0 && parseItem == 0) return false;
      if (parseItem < 0 || parseItem > 255) return false;
    }
    return true;
  }

  // 一方面，正则表达式本身是比较复杂的，写出完全没有bug的正则表达本身就比较有挑战；
  // 另一方面，并不是每个程序员都精通正则表达式
  // 所以，在这两种实现方式中，第二种实现方式更加“简单”，更加符合KISS原则
  public static void main(String[] args) {
    System.out.println(checkIp1("256.256.256.256"));
    System.out.println(checkIp1("255.255.255.255"));
    System.out.println(checkIp1("01.02.03.04"));
    System.out.println();
    System.out.println(checkIp1("192.168.01.1"));
    System.out.println(checkIp1("192.168.1.a"));
    System.out.println(checkIp1("192.168..1"));
  }
}
