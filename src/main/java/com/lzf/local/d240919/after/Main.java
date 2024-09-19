package com.lzf.local.d240919.after;

/**
 * @author leizefeng
 */
public class Main {

  public static void main(String[] args) {
    AlertCheckInfo alertCheckInfo = new AlertCheckInfo(
        "api",
        1L,
        1L,
        1L,
        1L
    );
    // 重构之后的代码更加灵活和易扩展。如果我们要想添加新的告警逻辑，
    // 只需要基于扩展的方式创建新的handler类即可，不需要改动原来的check()函数的逻辑。
    // 而且，我们只需要为新的handler类添加单元测试，老的单元测试都不会失败，也不用修改。
    AlertContext.getInstance().getAlert().check(alertCheckInfo);
  }
}
