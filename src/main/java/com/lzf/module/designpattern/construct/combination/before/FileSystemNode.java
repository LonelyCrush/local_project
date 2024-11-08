package com.lzf.module.designpattern.construct.combination.before;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author leizefeng
 */
public class FileSystemNode {

  private String path;

  private boolean isFile;

  private List<FileSystemNode> childrenNode = new ArrayList<>();

  public FileSystemNode(String path, boolean isFile) {
    this.path = path;
    this.isFile = isFile;
  }

  public String getPath() {
    return path;
  }

  public int countNumOfFiles() {
    if (isFile) {
      return 1;
    }

    int num = 0;
    for (FileSystemNode node : childrenNode) {
      num += node.countNumOfFiles();
    }
    return num;
  }

  public long countSizeOfFiles() {
    if (isFile) {
      File file = new File(path);
      if (!file.exists()) {
        return 0;
      }
      return file.length();
    }

    long size = 0;
    for (FileSystemNode node : childrenNode) {
      size += node.countSizeOfFiles();
    }
    return size;
  }

  public void addNode(FileSystemNode node) {
    childrenNode.add(node);
  }

  public void removeNode(FileSystemNode node) {
    int size = childrenNode.size();
    int i = 0;
    for (; i < size; i++) {
      if (childrenNode.get(i).getPath().equalsIgnoreCase(node.getPath())) {
        break;
      }
    }
    if (i < size) {
      childrenNode.remove(i);
    }
  }
}
