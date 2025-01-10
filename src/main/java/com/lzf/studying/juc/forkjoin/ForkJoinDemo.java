package com.lzf.studying.juc.forkjoin;

import com.lzf.studying.juc.util.FutureUtil;
import com.lzf.studying.juc.util.SleepUtil;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * @author leizefeng
 * <p> 分治算法
 * <p> 无返回值：{@link java.util.concurrent.RecursiveAction}
 * <p> 有返回值：{@link java.util.concurrent.RecursiveTask}
 */
@Slf4j
public class ForkJoinDemo {

  private static final int[] ARRAY =
      {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

  public static void main(String[] args) {
    ForkJoinDemo demo = new ForkJoinDemo();

    ForkJoinPool pool = ForkJoinPool.commonPool();
    demo.arraySum(pool);

    System.out.println();
    demo.arrayReplace(pool);
  }

  private void arraySum(@NotNull ForkJoinPool pool) {
    pool.execute(new StringSplitAction("Hello World"));

    SleepUtil.sleep();
    System.out.println();
    ArraySumTask arraySumTask = new ArraySumTask(ARRAY);
    ForkJoinTask<Integer> submit = pool.submit(arraySumTask);
    log.info("result: {}", FutureUtil.getResult(submit));

    SleepUtil.sleep();
    System.out.println();
    Integer invoke = pool.invoke(arraySumTask);
    log.info("invoke result: {}", invoke);

    SleepUtil.sleep();
  }

  private void arrayReplace(@NotNull ForkJoinPool pool) {
    ForkJoinTask<int[]> submit =
        pool.submit(new ArrayReplaceTask(ARRAY, 5, 10));
    log.info("result: {}", FutureUtil.getResult(submit));
  }
}
