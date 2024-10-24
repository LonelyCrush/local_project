package com.lzf.local.d2410.d241024.copy;

import lombok.Data;

/**
 * <p>
 * 该类用于：
 * </p>
 *
 * @author leizf
 * @since 2024-03-16 17:13
 */
public class DeepCopy {

  public static void main(String[] args) throws CloneNotSupportedException {

    @Data
    class Teacher implements Cloneable {

      private String name;
      private int age;

      public Object clone() throws CloneNotSupportedException {
        return super.clone();
      }
    }

    @Data
    class Student implements Cloneable {

      private String name;
      private int age;
      private Teacher teacher;

      public Object clone() throws CloneNotSupportedException {
        // 浅复制时：
        // Object object = super.clone();
        // return object;

        // 改为深复制：
        Student student = (Student) super.clone();
        // 本来是浅复制，现在将Teacher对象复制一份并重新set进来
        student.setTeacher((Teacher) student.getTeacher().clone());
        return student;
      }
    }

    Teacher teacher = new Teacher();
    teacher.setName("riemann");
    teacher.setAge(28);

    Student student1 = new Student();
    student1.setName("edgar");
    student1.setAge(18);
    student1.setTeacher(teacher);

    Student student2 = (Student) student1.clone();
    System.out.println("-------------拷贝后-------------");
    System.out.println(student2.getName());
    System.out.println(student2.getAge());
    System.out.println(student2.getTeacher().getName());
    System.out.println(student2.getTeacher().getAge());

    System.out.println("-------------修改老师的信息后-------------");
    // 修改老师的信息
    teacher.setName("jack");
    System.out.println("student1的teacher为： " + student1.getTeacher().getName());
    System.out.println("student2的teacher为： " + student2.getTeacher().getName());
  }
}
