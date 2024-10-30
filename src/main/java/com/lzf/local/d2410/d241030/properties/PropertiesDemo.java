package com.lzf.local.d2410.d241030.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author leizefeng
 */
public class PropertiesDemo {

  public static void main(String[] args) {
    Properties properties = new Properties();
    try (InputStream inputStream =
        PropertiesDemo.class.getClassLoader().getResourceAsStream("common/test.properties")) {
      if (inputStream == null) {
        System.out.println("无法找到文件 test.properties");
      }
      properties.load(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }

    System.out.println(properties.get("a"));
    System.out.println(properties.get("b"));
    System.out.println(properties.get("name"));
    System.out.println(properties.get("address"));
  }
}
