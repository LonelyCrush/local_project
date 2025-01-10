package com.lzf.studying.juc.util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.jetbrains.annotations.NotNull;

/**
 * @author leizefeng
 */
public class FutureUtil {

  public static <T> T getResult(@NotNull Future<T> future) {
    try {
      return future.get();
    } catch (InterruptedException | ExecutionException e) {
      throw new RuntimeException(e);
    }
  }
}
