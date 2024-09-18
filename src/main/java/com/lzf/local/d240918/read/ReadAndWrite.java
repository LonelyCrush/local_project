package com.lzf.local.d240918.read;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * @author leizefeng
 */
@Slf4j
public class ReadAndWrite {

  public static void main(String[] args) {
    String source = "src/main/java/com/lzf/local/d240918/read/source.txt";
    String target = "src/main/java/com/lzf/local/d240918/read/target.txt";
    readAndWrite(source, target);
  }

  private static void readAndWrite(String source, String target) {
    List<String> sourceLines = new ArrayList<>();
    List<User> users = new ArrayList<>();
    List<String> targetLines = new ArrayList<>();
    try {
      sourceLines = Files.readAllLines(Paths.get(source));
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (sourceLines.size() > 0) {
      for (int i = 0; i < sourceLines.size(); i++) {
        try {
          User user = parseUser(sourceLines.get(i));
          users.add(user);
          users.sort(Comparator.comparing(User::getAge));
          targetLines.add(user.getName() + "\t" + user.getAge() + "\t" + user.getGender());
        } catch (Exception e) {
          log.error("错误，行号：" + (i + 1));
          e.printStackTrace();
        }
      }
    }
    try {
      Files.write(Paths.get(target), targetLines);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static User parseUser(String text) {
    String[] strings = text.split("&");
    return new User(strings[0], Integer.parseInt(strings[1]), strings[2]);
  }
}
