package com.lzf.modules.designpattern.create.factory.simplefactory.impl;

import com.lzf.modules.designpattern.create.factory.simplefactory.IRuleConfigParser;
import com.lzf.modules.designpattern.create.factory.simplefactory.RuleConfig;

/**
 * @author leizefeng
 */
public class JsonRuleConfigParser implements IRuleConfigParser {

  @Override
  public RuleConfig parse(String configText) {
    return new RuleConfig().setName("json");
  }
}
