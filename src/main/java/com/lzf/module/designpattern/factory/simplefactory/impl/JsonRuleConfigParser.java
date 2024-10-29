package com.lzf.module.designpattern.factory.simplefactory.impl;

import com.lzf.module.designpattern.factory.simplefactory.IRuleConfigParser;
import com.lzf.module.designpattern.factory.simplefactory.RuleConfig;

/**
 * @author leizefeng
 */
public class JsonRuleConfigParser implements IRuleConfigParser {

  @Override
  public RuleConfig parse(String configText) {
    return new RuleConfig().setName("json");
  }
}
