package com.lzf.modules.designpattern.behavior.strategyreflect.service.impl;

import com.lzf.modules.designpattern.behavior.strategyreflect.annotation.Strategy;
import com.lzf.modules.designpattern.behavior.strategyreflect.service.StrategyService;
import org.springframework.stereotype.Service;

/**
 * @author leizefeng
 */
@Strategy("two")
@Service
public class StrategyServiceTwoImpl implements StrategyService {

  @Override
  public void task() {
    System.out.println("Executing Strategy Two");
  }
}
