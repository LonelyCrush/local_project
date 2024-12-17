package com.lzf.local.d2412.d241217;

import cn.hutool.crypto.digest.BCrypt;

/**
 * @author leizefeng
 */
public class BcryptDemo {

  public static void main(String[] args) {
    BcryptDemo demo = new BcryptDemo();
    demo.test1();
  }

  public void test1() {
    // 加密
    String password = "123456";
    String encryptPassword = BCrypt.hashpw(password, BCrypt.gensalt());
    System.out.println(encryptPassword);
    System.out.println(BCrypt.hashpw(password, encryptPassword));

    // 使用正确密码验证密码是否正确
    boolean flag = BCrypt.checkpw(password, encryptPassword);
    System.out.println(flag);
  }
}
