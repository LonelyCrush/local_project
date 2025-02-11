package com.lzf.studying.juc.future;

import com.lzf.studying.juc.util.SleepUtil;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author leizefeng
 */
@Slf4j
public class FutureDemo {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    FutureDemo demo = new FutureDemo();

    demo.test2();
  }

  @AllArgsConstructor
  private static class SquareCalculator {
    private ExecutorService executor;

    public Future<Integer> calculate(Integer input) {
      return executor.submit(() -> {
        SleepUtil.sleep(1000);
        return input * input;
      });
    }
  }

  private void test1() throws ExecutionException, InterruptedException {
    SquareCalculator squareCalculator = new SquareCalculator(Executors.newSingleThreadExecutor());
    Future<Integer> future = squareCalculator.calculate(14);
    while (!future.isDone()) {
      log.info("waiting for result");
      SleepUtil.sleep(400);
    }
    // get是阻塞的，所以可以先用isDone判断是否完成， 或者使用get(timeout, unit)设置超时时间
    log.info("result: {}", future.get());
    // 如果不在关注结果，可以直接cancel取消任务，但是取消之后永远不会完成其操作了
    // cancel() 方法之后立即调用 get() 方法，将会获得一个 CancellationException 异常会抛出异常
    // future.cancel(true);
  }

  private void test2() throws ExecutionException, InterruptedException {
//    SquareCalculator squareCalculator =
//        new SquareCalculator(Executors.newFixedThreadPool(1));
    SquareCalculator squareCalculator =
        new SquareCalculator(Executors.newFixedThreadPool(2));
    Future<Integer> future1 = squareCalculator.calculate(14);
    Future<Integer> future2 = squareCalculator.calculate(15);
    while (!(future1.isDone() && future2.isDone())) {
      log.info(
          String.format("result1: %s, result2: %s",
              future1.isDone() ? "done" : "not done",
              future2.isDone() ? "done" : "not done")
      );
      SleepUtil.sleep(399);
    }
    log.info("result1: {}, result2: {}", future1.get(), future2.get());
  }
}
