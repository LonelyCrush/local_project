package com.lzf.local.d2409.d240912;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzf.d240819.entity.Student;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.cglib.beans.BeanMap;

/**
 * @author leizefeng
 */
public class BeanAndMap {

  public static void main(String[] args) throws Exception {
//    Student student = new Student();
//    student.setId(1);
//    BeanMap beanMap = BeanMap.create(student);
//    System.out.println(beanMap);
//    beanMap.put("name", "Jack");
//    System.out.println(beanMap);
//    student.setName("Jack");
//    System.out.println(beanMap);
    test5();
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

  private static void test4() {
    Map<String, Object> map = new LinkedHashMap<>();
    map.put("id", 1);
    map.put("name", "Fred");
    map.put("age", 21);
    map.put("birth", "2003-10-13T00:00");
    Student student = new Student();
    BeanUtil.copyProperties(map, student);
    System.out.println(student);
  }

  private static void test5() throws JsonProcessingException {
//    Map<String, Object> map = new LinkedHashMap<>();
//    Student student = new Student();
//    student
//        .setId(1)
//        .setName("Fred")
//        .setAge(21)
//        .setBirth(LocalDateTime.of(2003, 10, 13, 0, 0));
//    map.put("other", 3);
//    map.put("student", student);
//    System.out.println(map);
//    System.out.println();
//
//    ObjectMapper objectMapper = new ObjectMapper();
//    String mapString = null;
//    try {
//      mapString = objectMapper.writeValueAsString(map);
//    } catch (JsonProcessingException e) {
//      e.printStackTrace();
//    }
//    System.out.println(mapString);
//    System.out.println();

    String jsonString = "{\"other\":3,\"student\":{\"id\":1,\"name\":\"Fred\",\"age\":21,\"birth\":\"2025/01/01\"}}";
    Map<String, Object> map = (Map<String, Object>) new ObjectMapper().readValue(jsonString, Map.class).get("student");
    Student student = new Student();
    BeanUtil.copyProperties(map, student);
    System.out.println(student);
  }
}
