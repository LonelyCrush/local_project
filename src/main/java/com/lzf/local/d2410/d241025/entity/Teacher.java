package com.lzf.local.d2410.d241025.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author leizefeng
 */
@Data
@Accessors(chain = true)
public class Teacher implements Cloneable, Serializable {

  private String name;

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }
}
