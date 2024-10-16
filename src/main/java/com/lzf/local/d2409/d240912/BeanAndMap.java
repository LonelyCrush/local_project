package com.lzf.local.d2409.d240912;

import cn.hutool.core.bean.BeanUtil;
import com.lzf.d240819.entity.Student;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.cglib.beans.BeanMap;

/**
 * @author leizefeng
 */
public class BeanAndMap {

  public static void main(String[] args) throws Exception {
    Student student = new Student();
    student.setId(1);
    BeanMap beanMap = BeanMap.create(student);
    System.out.println(beanMap);
    beanMap.put("name", "Jack");
    System.out.println(beanMap);
    student.setName("Jack");
    System.out.println(beanMap);
  }

  private static void test2() {
    Student student = new Student();
    student
        .setId(1)
        .setName("Fred")
        .setAge(21)
        .setBirth(LocalDateTime.of(2003, 10, 13, 0, 0));
    Map<String, Object> studentMap = BeanUtil.beanToMap(student);
    System.out.println(studentMap);
  }

  private static void test3() {
    Map<String, Object> map = new HashMap<>();
    map.put("Id", 1);
    map.put("Name", "Fred");
    map.put("age", 21);
    map.put("birth", "2003/10/13");
    Student student = new Student();
    BeanUtil.copyProperties(map, student, true);
    System.out.println(student);
  }
}
