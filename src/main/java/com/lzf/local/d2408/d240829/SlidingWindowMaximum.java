package com.lzf.local.d2408.d240829;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author leizefeng
 */
public class SlidingWindowMaximum {

  public static void main(String[] args) {
    int[] arrays = {1, 3, -1, 1, 1, 3, 6, 7};
    int[] arrays1 = {1, 2, 3, 4, 5, 6, 7, 8};
    int windowLength = 3;
    int[] result = slidingWindowMaximum(arrays, windowLength);
    int[] result1 = slidingWindowMaximum(arrays1, windowLength);
    System.out.println(Arrays.toString(result));
    System.out.println(Arrays.toString(result1));
  }

  private static int[] slidingWindowMaximum(int[] arrays, int windowLength) {
    // 数组的长度应该大于等于窗口长度
    if (arrays.length < windowLength) {
      return null;
    }

    // 数组长度为1时
    if (arrays.length == 1) {
      return arrays;
    }

    List<Integer> result = new ArrayList<>();
    MyQueue myQueue = new MyQueue();
    // 初始窗口
    for (int i = 0; i < windowLength; i++) {
      myQueue.rightPush(arrays[i]);
    }
    result.add(myQueue.getMax());
    // 开始滑动
    for (int i = windowLength; i < arrays.length; i++) {
      myQueue.leftPop(arrays[i - windowLength]);
      myQueue.rightPush(arrays[i]);
      result.add(myQueue.getMax());
    }

    return result.stream().mapToInt(Integer::intValue).toArray();
  }

  static class MyQueue {
    // 每次滑动伴随着一次左边pop和右边push
    ArrayDeque<Integer> queue = new ArrayDeque<>();

    public void leftPop(int value) {
      while (!queue.isEmpty() && value == queue.getFirst()) {
        queue.pollFirst();
      }
    }

    public void rightPush(int value) {
      while (!queue.isEmpty() && value > queue.getFirst()) {
        queue.pollLast();
      }
      queue.addLast(value);
    }

    public int getMax() {
      return queue.isEmpty()? -1 : queue.getFirst();
    }
  }
}
