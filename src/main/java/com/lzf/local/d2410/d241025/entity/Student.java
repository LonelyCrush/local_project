package com.lzf.local.d2410.d241025.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author leizefeng
 */
@Data
@Accessors(chain = true)
public class Student implements Cloneable, Serializable {

  private String name;

  private Teacher teacher;

  @Override
  protected Object clone() throws CloneNotSupportedException {
    Student student = (Student) super.clone();
    return student.setTeacher((Teacher) student.getTeacher().clone());
  }
}
