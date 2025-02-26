package com.lzf.local.d2501.d250212;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author leizefeng
 */
public class MainDemo {

  public static void main(String[] args) {
    MainDemo demo = new MainDemo();

    demo.test1();
    demo.test2();
    demo.test3();
  }

  /**
   * Mono代表的是单个或者空的数据。
   * 它可以发出一个元素，或者一个错误信号，或者什么都不发出就完成。
   */
  private void test1() {
    Mono<String> monoString = Mono.just("小黑的Mono示例");
    monoString.subscribe(System.out::println);
  }

  /**
   * 与Mono不同，Flux表示的是0到N个元素的序列。
   * 它可以发出多个元素，也可以发出错误信号，或者什么都不发出就完成。
   */
  private void test2() {
    Flux<Integer> fluxNumbers = Flux.range(1, 5);
    fluxNumbers.subscribe(
        number -> System.out.println("处理的数字: " + number),
        error -> System.err.println("错误信息: " + error),
        () -> System.out.println("处理完成!")
    );
  }

  private void test3() {
    Flux.range(1, 10)
        .filter(n -> n % 2 != 0)
        .map(n -> "奇数: " + n)
        .subscribe(
            System.out::println,
            error -> System.err.println("错误信息: " + error),
            () -> System.out.println("处理完成!"));
  }
}
