package com.lzf.local.d2410.d241025.config;

import com.lzf.local.d2410.d241025.entity.Student;
import com.lzf.local.d2410.d241025.entity.Teacher;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author leizefeng
 */
public class AsyncUtil {

  public static final ThreadLocal<Map<String, Object>> commonThreadLocal = new ThreadLocal<>();

  public static void setAsyncMap(Map<String, Object> asyncMap) {
    commonThreadLocal.set(asyncMap);
  }

  public static Map<String, Object> getAsyncMap() {
    return commonThreadLocal.get();
  }

  public static void main(String[] args) {
    Teacher teacher = new Teacher().setName("teacher1");
    Student student = new Student().setName("student1").setTeacher(teacher);
    Map<String, Object> map1 = new HashMap<>();
    map1.put("teacher", teacher);
    map1.put("student", student);
    Map<String, Object> map2 = deepCopyMap(map1);
    teacher.setName("teacher2");
    student.setName("student2");
    Teacher teacher2 = (Teacher) map2.get("teacher");
    Student student2 = (Student) map2.get("student");
    System.out.println(teacher2);
    System.out.println(student2);
  }

  public static Map<String, Object> deepCopyMap(Map<String, Object> original) {
    try {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      ObjectOutputStream oos = new ObjectOutputStream(bos);
      oos.writeObject(original);
      oos.flush();
      ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
      ObjectInputStream ois = new ObjectInputStream(bis);
      return (Map<String, Object>) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }
}
