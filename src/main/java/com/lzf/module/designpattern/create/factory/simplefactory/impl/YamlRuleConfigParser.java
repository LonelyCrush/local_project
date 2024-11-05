package com.lzf.module.designpattern.create.factory.simplefactory.impl;

import com.lzf.module.designpattern.create.factory.simplefactory.IRuleConfigParser;
import com.lzf.module.designpattern.create.factory.simplefactory.RuleConfig;

/**
 * @author leizefeng
 */
public class YamlRuleConfigParser implements IRuleConfigParser {

  @Override
  public RuleConfig parse(String configText) {
    return new RuleConfig().setName("yaml");
  }
}
