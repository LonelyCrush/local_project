package com.lzf.local.d2410.d241028.lastday;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * @author leizefeng
 */
public class CalendarDemo {

  public static void main(String[] args) {
    CalendarDemo demo = new CalendarDemo();

    demo.isLastDayOfMonth(LocalDateTime.now());
    demo.isLastDayOfMonth(LocalDateTime.of(
        2024, 10, 31, 1, 0));

    demo.isLastDayOfMonth(new Date());
    demo.isLastDayOfMonth(new Date(2024, Calendar.OCTOBER, 31, 0, 0));
  }

  private void isLastDayOfMonth(LocalDateTime localDateTime) {
    // 获取当前月份的最后一天
    LocalDateTime lastDayOfMonth =
        localDateTime.withDayOfMonth(localDateTime.toLocalDate().lengthOfMonth());

    // 判断是否是最后一天
    if (localDateTime.equals(lastDayOfMonth)) {
      System.out.println("是当月的最后一天");
    } else {
      System.out.println("不是当月的最后一天");
    }
  }

  private void isLastDayOfMonth(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    if (calendar.get(Calendar.DAY_OF_MONTH) == calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
      System.out.println("是当月的最后一天");
    } else {
      System.out.println("不是当月的最后一天");
    }
  }
}
