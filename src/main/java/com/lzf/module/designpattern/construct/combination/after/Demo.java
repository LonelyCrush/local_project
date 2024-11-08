package com.lzf.module.designpattern.construct.combination.after;

/**
 * @author leizefeng
 */
public class Demo {

  /**
   * /
   * /wz/
   * /wz/a.txt
   * /wz/b.txt
   * /wz/movies/
   * /wz/movies/c.avi
   * /xzg/
   * /xzg/docs/
   * /xzg/docs/d.txt
   */
  public static void main(String[] args) {
    Directory fileSystemTree = new Directory("/");

    Directory node_wz = new Directory("/wz/");
    Directory node_xzg = new Directory("/xzg/");
    fileSystemTree.addNode(node_wz);
    fileSystemTree.addNode(node_xzg);

    File node_wz_a = new File("/wz/a.txt");
    File node_wz_b = new File("/wz/b.txt");
    Directory node_wz_movies = new Directory("/wz/movies/");
    node_wz.addNode(node_wz_a);
    node_wz.addNode(node_wz_b);
    node_wz.addNode(node_wz_movies);

    Directory node_xzg_docs = new Directory("/xzg/docs/");
    node_xzg.addNode(node_xzg_docs);

    File node_wz_movies_c = new File("/wz/movies/c.avi");
    node_wz_movies.addNode(node_wz_movies_c);

    File node_xzg_docs_d = new File("/xzg/docs/d.txt");
    node_xzg_docs.addNode(node_xzg_docs_d);

    System.out.println("/ files num:" + fileSystemTree.countNumOfFiles());
    System.out.println("/wz/ files num:" + node_wz.countNumOfFiles());
  }
}
