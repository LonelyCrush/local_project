package com.lzf.module.designpattern.construct.combination.after;

import java.util.ArrayList;
import java.util.List;

/**
 * @author leizefeng
 */
public class Directory extends FileSystemNode {

  private List<FileSystemNode> childrenNode = new ArrayList<>();

  public Directory(String path) {
    super(path);
  }

  public void addNode(FileSystemNode node) {
    childrenNode.add(node);
  }

  public void removeNode(FileSystemNode node) {
    int size = childrenNode.size();
    int i = 0;
    for (; i < size; i++) {
      if (childrenNode.get(i).getPath().equalsIgnoreCase(node.path)) {
        break;
      }
    }
    if (i < size) {
      childrenNode.remove(i);
    }
  }

  @Override
  public int countNumOfFiles() {
    int num = 0;
    for (FileSystemNode node : childrenNode) {
      num += node.countNumOfFiles();
    }
    return num;
  }

  @Override
  public long countSizeOfFiles() {
    long size = 0;
    for (FileSystemNode node : childrenNode) {
      size += node.countSizeOfFiles();
    }
    return size;
  }
}
