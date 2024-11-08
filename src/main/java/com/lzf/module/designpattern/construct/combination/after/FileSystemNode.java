package com.lzf.module.designpattern.construct.combination.after;

/**
 * @author leizefeng
 */
public abstract class FileSystemNode {

  protected String path;

  public FileSystemNode(String path) {
    this.path = path;
  }

  public String getPath() {
    return path;
  }

  public abstract int countNumOfFiles();

  public abstract long countSizeOfFiles();
}
