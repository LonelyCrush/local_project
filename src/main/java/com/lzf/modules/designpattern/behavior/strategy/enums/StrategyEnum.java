package com.lzf.modules.designpattern.behavior.strategy.enums;

import lombok.Getter;

/**
 * @author leizefeng
 */
@Getter
public enum StrategyEnum {

  STRATEGY_TYPE_ONE("one"),
  STRATEGY_TYPE_TWO("two");

  private final String strategyType;

  StrategyEnum(String strategyType) {
    this.strategyType = strategyType;
  }
}
