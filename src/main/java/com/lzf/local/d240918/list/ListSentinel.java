package com.lzf.local.d240918.list;

/**
 * @author leizefeng
 */
public class ListSentinel {

  // 一般情况下
  int find(char[] source, char target) {
    int length = source.length;
    if (length == 0) {
      return -1;
    }
    int i = 0;
    while (i < length) {
      if (source[i] == target) {
        return i;
      }
      i++;
    }
    return -1;
  }

  // 使用哨兵在遍历次数多时性能更好，但是可读性很差
  int findWithSentinel(char[] source, char target) {
    int length = source.length;
    if (length == 0) {
      return -1;
    }
    source[length - 1] = target;
    int i = 0;
    while (source[i] != target) {
      i++;
    }
    if (i == length - 1) {
      return -1;
    }
    return i;
  }
}
