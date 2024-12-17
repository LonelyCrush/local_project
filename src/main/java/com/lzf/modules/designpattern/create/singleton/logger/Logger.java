package com.lzf.modules.designpattern.create.singleton.logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author leizefeng
 */
public class Logger {

  private FileWriter writer;

  private static final Logger instance = new Logger();

  private Logger() {
    File file = new File(
        "src/main/java/com/lzf/module/designpattern/singleton/logger.txt");
    try {
      writer = new FileWriter(file, true);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void log(String message) {
    try {
      writer.write(message + "\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void endLog() {
    try {
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Logger getInstance() {
    return instance;
  }

  public static void main(String[] args) {
    Logger logger = Logger.getInstance();
    logger.log("First");
    logger.log("Second");
    // 刷新或者关闭writer之后才会真正写入文件
    logger.endLog();
  }
}
