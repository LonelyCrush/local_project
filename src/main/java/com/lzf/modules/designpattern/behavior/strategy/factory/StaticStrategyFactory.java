package com.lzf.modules.designpattern.behavior.strategy.factory;

import com.lzf.modules.designpattern.behavior.strategy.service.StrategyService;
import com.lzf.modules.designpattern.behavior.strategy.enums.StrategyEnum;
import com.lzf.modules.designpattern.behavior.strategy.service.impl.StrategyServiceOneImpl;
import com.lzf.modules.designpattern.behavior.strategy.service.impl.StrategyServiceTwoImpl;
import java.util.HashMap;
import java.util.Map;
import org.springframework.util.StringUtils;

/**
 * @author leizefeng
 */
public class StaticStrategyFactory {

  private static final Map<StrategyEnum, StrategyService> strategyMap = new HashMap<>();

  // 而实际上这一步关于表的配置可以转移到配置文件中的键值对当中
  static {
    strategyMap.put(StrategyEnum.STRATEGY_TYPE_ONE, new StrategyServiceOneImpl());
    strategyMap.put(StrategyEnum.STRATEGY_TYPE_TWO, new StrategyServiceTwoImpl());
  }

  public static StrategyService getStrategy(StrategyEnum strategyEnum) {
    if (strategyEnum == null || !StringUtils.hasText(strategyEnum.getStrategyType())) {
      throw new IllegalArgumentException("strategyName cannot be empty or null");
    }
    if (StrategyEnum.STRATEGY_TYPE_ONE.equals(strategyEnum)) {
      return strategyMap.get(StrategyEnum.STRATEGY_TYPE_ONE);
    }
    if (StrategyEnum.STRATEGY_TYPE_TWO.equals(strategyEnum)) {
      return strategyMap.get(StrategyEnum.STRATEGY_TYPE_TWO);
    }
    throw new IllegalArgumentException("Invalid strategyName: " + strategyEnum.getStrategyType());
  }
}
