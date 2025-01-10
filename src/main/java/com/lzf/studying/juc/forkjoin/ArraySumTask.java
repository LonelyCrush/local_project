package com.lzf.studying.juc.forkjoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

/**
 * @author leizefeng
 */
@Slf4j
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArraySumTask extends RecursiveTask<Integer> {

  private int[] array;

  private static final int THRESHOLD = 5;

  @Override
  protected Integer compute() {
    Integer result = 0;
    if (array.length > THRESHOLD) {
      List<ArraySumTask> taskList = splitTask(array);
//      result = invokeAll(taskList).stream().mapToInt(ForkJoinTask::join).sum();
      // 等同于
      // 此处 invokeAll 顺序实际上是能够保持与拆分任务时的顺序一致
      Collection<ArraySumTask> taskCollection = invokeAll(taskList);
      for (ArraySumTask task : taskCollection) {
        Integer partResult = task.join();
        log.info("partResult: {}", partResult);
        result += partResult;
      }
    } else {
      result = doTask(array);
    }
    return result;
  }

  @NotNull
  private List<ArraySumTask> splitTask(@NotNull int[] array) {
    List<ArraySumTask> taskList = new ArrayList<>();
    for (int i = 0; i < array.length; i += THRESHOLD) {
      int end = Math.min(i + THRESHOLD, array.length);
      int[] partArray = new int[end - i];
      System.arraycopy(array, i, partArray, 0, end - i);
      taskList.add(new ArraySumTask(partArray));
    }
    return taskList;
  }

  @NotNull
  private Integer doTask(int[] array) {
    return Arrays.stream(array).sum();
  }
}
