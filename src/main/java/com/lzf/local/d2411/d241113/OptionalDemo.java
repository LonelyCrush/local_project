package com.lzf.local.d2411.d241113;

import com.lzf.d240819.entity.Student;
import java.util.Optional;

/**
 * @author leizefeng
 */
public class OptionalDemo {

  public static void main(String[] args) {
    Student student = null;

//    String name = student.getName();

//    String name = Optional.ofNullable(student)
//        .map(s -> s.getName())
//        .orElseThrow(() -> new IllegalArgumentException("student cannot be null"));

    String name = Optional.ofNullable(student)
        .map(s -> s.getName())
        .orElseGet(() -> {
          return null;
        });

    System.out.println(name);
  }
}
