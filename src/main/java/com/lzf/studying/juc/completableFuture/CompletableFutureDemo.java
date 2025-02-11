package com.lzf.studying.juc.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author leizefeng
 */
@Slf4j
public class CompletableFutureDemo {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    CompletableFutureDemo demo = new CompletableFutureDemo();

    demo.test1();
    demo.test2();
    demo.test3();
  }

  public void test1() throws ExecutionException, InterruptedException {
    CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
    CompletableFuture<String> future2 = future1.thenApply(s -> s + " World");
    System.out.println(future2.get());

    // 实际上是可以链式的
    CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> "Hello")
        .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
    System.out.println(future3.get());
  }

  public void test2() throws ExecutionException, InterruptedException {
    String name = null;
    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
      if (name == null) {
        throw new RuntimeException("Computation error!");
      }
      return "Hello, " + name;
      // 此handle() 方法接收接收两个参数：
      // 计算结果（ 如果成功完成 ）和抛出异常（ 如果某些计算步骤未正常完成 ）
    }).handle((s, t) -> s != null ? s : "Hello, Stranger!");
    System.out.println(future.get());
  }

  public void test3() throws ExecutionException, InterruptedException {
    CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "Hello");
    // 在幕后，函数的应用程序被包装到 ForkJoinTask 实例中
    CompletableFuture<String> future2 = future1.thenApplyAsync(s -> s + " World");
    CompletableFuture<String> future3 = future1.thenApply(s -> s + " World");
    log.info(future2.get());
    log.info(future3.get());
  }
}
