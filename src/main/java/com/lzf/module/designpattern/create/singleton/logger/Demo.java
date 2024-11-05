package com.lzf.module.designpattern.create.singleton.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author leizefeng
 */
public class Demo {

  public static void main(String[] args) {
    // 文件路径
    // 请替换为你的实际文件路径
    File file = new File(
        "src/main/java/com/lzf/module/designpattern/singleton/logger.txt");

    FileWriter fileWriter = null;
    try {
      // 创建 FileWriter 对象，第二个参数为 true 表示以追加模式写入
      fileWriter = new FileWriter(file, true);

      // 写入内容
      fileWriter.write("这是要写入的内容\n"); // 在文件中写入一行文本

    } catch (IOException e) {
      e.printStackTrace(); // 捕获并打印异常信息
    } finally {
      // 确保 FileWriter 被关闭
      try {
        if (fileWriter != null) {
          fileWriter.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

}
