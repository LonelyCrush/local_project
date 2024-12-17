package com.lzf.modules.designpattern.behavior.strategy.factory;

import com.lzf.modules.designpattern.behavior.strategy.service.StrategyService;
import com.lzf.modules.designpattern.behavior.strategy.enums.StrategyEnum;
import com.lzf.modules.designpattern.behavior.strategy.service.impl.StrategyServiceOneImpl;
import com.lzf.modules.designpattern.behavior.strategy.service.impl.StrategyServiceTwoImpl;
import org.springframework.util.StringUtils;

/**
 * @author leizefeng
 */
public class DynamicStrategyFactory {

  public static StrategyService getStrategyService(StrategyEnum strategyEnum) {
    if (strategyEnum == null || !StringUtils.hasText(strategyEnum.getStrategyType())) {
      throw new IllegalArgumentException("strategyName cannot be empty or null");
    }
    if (StrategyEnum.STRATEGY_TYPE_ONE.equals(strategyEnum)) {
      return new StrategyServiceOneImpl();
    }
    if (StrategyEnum.STRATEGY_TYPE_TWO.equals(strategyEnum)) {
      return new StrategyServiceTwoImpl();
    }
    throw new IllegalArgumentException("Invalid strategyName: " + strategyEnum.getStrategyType());
  }
}
