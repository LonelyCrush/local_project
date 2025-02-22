package com.lzf.modules.designpattern.create.builder;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.Getter;

/**
 * @author leizefeng
 */
@Getter
public class ResourcePoolConfig {

  private final String name;
  private final int maxTotal;
  private final int maxIdle;
  private final int minIdle;

  private ResourcePoolConfig(Builder builder) {
    this.name = builder.name;
    this.maxTotal = builder.maxTotal;
    this.maxIdle = builder.maxIdle;
    this.minIdle = builder.minIdle;
  }

  // 我们将Builder类设计成了ResourcePoolConfig的内部类。
  // 我们也可以将Builder类设计成独立的非内部类ResourcePoolConfigBuilder。
  public static class Builder {

    private static final int DEFAULT_MAX_TOTAL = 8;
    private static final int DEFAULT_MAX_IDLE = 8;
    private static final int DEFAULT_MIN_IDLE = 0;

    private String name;
    private int maxTotal = DEFAULT_MAX_TOTAL;
    private int maxIdle = DEFAULT_MAX_IDLE;
    private int minIdle = DEFAULT_MIN_IDLE;

    public ResourcePoolConfig build() {
      // 校验逻辑放到这里来做，包括必填项校验、依赖关系校验、约束条件校验等
      if (StringUtils.isBlank(name)) {
        throw new IllegalArgumentException("...");
      }
      if (maxIdle > maxTotal) {
        throw new IllegalArgumentException("...");
      }
      if (minIdle > maxTotal || minIdle > maxIdle) {
        throw new IllegalArgumentException("...");
      }
      return new ResourcePoolConfig(this);
    }

    public Builder setName(String name) {
      if (StringUtils.isBlank(name)) {
        throw new IllegalArgumentException("...");
      }
      this.name = name;
      return this;
    }

    public Builder setMaxTotal(int maxTotal) {
      if (maxTotal <= 0) {
        throw new IllegalArgumentException("...");
      }
      this.maxTotal = maxTotal;
      return this;
    }

    public Builder setMaxIdle(int maxIdle) {
      if (maxIdle < 0) {
        throw new IllegalArgumentException("...");
      }
      this.maxIdle = maxIdle;
      return this;
    }

    public Builder setMinIdle(int minIdle) {
      if (minIdle < 0) {
        throw new IllegalArgumentException("...");
      }
      this.minIdle = minIdle;
      return this;
    }
  }

  public static void main(String[] args) {
    ResourcePoolConfig resourcePoolConfig =
        new ResourcePoolConfig.Builder()
           .setName("default-resource-pool")
           .setMaxTotal(10)
           .setMaxIdle(5)
           .setMinIdle(2)
           .build();
    System.out.println(resourcePoolConfig.getName());
    System.out.println(resourcePoolConfig.getMaxTotal());
    System.out.println(resourcePoolConfig.getMaxIdle());
    System.out.println(resourcePoolConfig.getMinIdle());
  }
}
