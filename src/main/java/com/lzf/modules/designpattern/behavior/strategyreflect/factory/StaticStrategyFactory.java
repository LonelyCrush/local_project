package com.lzf.modules.designpattern.behavior.strategyreflect.factory;

import com.lzf.modules.designpattern.behavior.strategy.service.StrategyService;
import com.lzf.modules.designpattern.behavior.strategyreflect.annotation.Strategy;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * @author leizefeng
 */
public class StaticStrategyFactory {

  private static final Map<String, StrategyService> strategyMap = new HashMap<>();

  static {
    loadStrategy();
  }

  private static void loadStrategy() {
    ServiceLoader<StrategyService> loader = ServiceLoader.load(StrategyService.class);
    for (StrategyService strategyService : loader) {
      Class<?> strategyServiceClass = strategyService.getClass();
      if (strategyServiceClass.isAnnotationPresent(Strategy.class)) {
        Strategy strategy = strategyServiceClass.getAnnotation(Strategy.class);
        String strategyType = strategy.value();
        strategyMap.put(strategyType, strategyService);
      }
    }
  }

  public static StrategyService getStrategy(String strategyType) {
    return strategyMap.get(strategyType);
  }
}
