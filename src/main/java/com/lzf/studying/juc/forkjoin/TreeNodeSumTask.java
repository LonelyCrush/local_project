package com.lzf.studying.juc.forkjoin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author leizefeng
 */

@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class TreeNodeSumTask extends RecursiveTask<Integer> {

  private TreeNode treeNode;

  static class TreeNode {
    int value;
    Set<TreeNode> children;

    TreeNode(int value, TreeNode... children) {
      this.value = value;
      this.children = new HashSet<>(Arrays.asList(children));
    }
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1,
        new TreeNode(2, new TreeNode(4), new TreeNode(5)),
        new TreeNode(3, new TreeNode(6), new TreeNode(7)));

    ForkJoinPool pool = ForkJoinPool.commonPool();
    Integer invoke = pool.invoke(new TreeNodeSumTask(root));
    log.info("invoke = {}", invoke);
  }

  @Override
  protected Integer compute() {
    return treeNode.value +
        treeNode.children.stream()
            .map(children -> new TreeNodeSumTask(children).fork())
            .mapToInt(ForkJoinTask::join)
            .sum();
  }
}
