package com.lzf.studying.juc.forkjoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author leizefeng
 */
@Slf4j
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArrayReplaceTask extends RecursiveTask<int[]> {

  private int[] array;

  private int unitLength;

  private int mouldLength;

  @Override
  protected int[] compute() {
    if (array.length <= unitLength) {
      return doTask(array, mouldLength);
    }

    int[] result = new int[array.length];
    int currentIndex = 0;
//    Collection<ArrayReplaceTask> taskCollection =
//        invokeAll(splitTask(array, unitLength, mouldLength));
//    for (ArrayReplaceTask task : taskCollection) {
//      int[] join = task.join();
//      log.info("join result: {}", join);
//      System.arraycopy(join, 0, result, currentIndex, join.length);
//      currentIndex += join.length;
//    }
//    return result;
    return invokeAll(splitTask(array, unitLength, mouldLength)).stream()
        .map((task) -> {
          int[] join = task.join();
          log.info("join result: {}", join);
          return join;
        })
        // 将每个任务的结果数组流扁平化为一个int流
        .flatMapToInt(Arrays::stream)
        // 收集为一个int数组
        .toArray();
  }

  private int[] doTask(int[] array, int mouldLength) {
    if (array == null || array.length == 0 || mouldLength <= 0) {
      throw new IllegalArgumentException("Invalid array");
    }

    int[] result = new int[array.length];
    for (int i = 0; i < result.length; i++) {
      result[i] = array[i] % mouldLength;
    }
    return result;
  }

  private List<ArrayReplaceTask> splitTask(int[] array, int unitLength, int mouldLength) {
    if (array == null || array.length == 0 || unitLength <= 0 || mouldLength <= 0) {
      throw new IllegalArgumentException("Invalid array or unitLength");
    }

    List<ArrayReplaceTask> taskList = new ArrayList<>();
    for (int i = 0; i < array.length; i += unitLength) {
      int[] partArray = new int[Math.min(i + unitLength, array.length) - i];
      System.arraycopy(array, i, partArray, 0, partArray.length);
      taskList.add(new ArrayReplaceTask(partArray, unitLength, mouldLength));
    }
    return taskList;
  }
}
