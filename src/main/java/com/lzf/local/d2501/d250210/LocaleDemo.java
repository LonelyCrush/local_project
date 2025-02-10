package com.lzf.local.d2501.d250210;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author leizefeng
 */
public class LocaleDemo {

  public static void main(String[] args) {
    LocaleDemo demo = new LocaleDemo();

    demo.testDefaultLocale();
    demo.testResourceBundleZH();
    demo.testResourceBundleUS();
  }

  public void testDefaultLocale() {
    Locale locale = Locale.getDefault();
    System.out.println("Locale.getDefault() = " + locale);
    System.out.println("Locale.China = " + Locale.CHINA);
  }

  public void testResourceBundleZH() {
    ResourceBundle bundle = ResourceBundle.getBundle("messages");
    System.out.println(bundle.getString("info"));
    System.out.println(MessageFormat.format(bundle.getString("info.format"),
        "0", "1"));
  }

  public void testResourceBundleUS() {
    ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.US);
    System.out.println(bundle.getString("info"));
    System.out.println(MessageFormat.format(bundle.getString("info.format"),
        "0", "1"));
  }
}
