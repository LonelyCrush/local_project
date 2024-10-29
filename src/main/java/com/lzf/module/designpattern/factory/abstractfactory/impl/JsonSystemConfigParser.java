package com.lzf.module.designpattern.factory.abstractfactory.impl;

import com.lzf.module.designpattern.factory.abstractfactory.ISystemConfigParser;
import com.lzf.module.designpattern.factory.simplefactory.IRuleConfigParser;
import com.lzf.module.designpattern.factory.simplefactory.RuleConfig;

/**
 * @author leizefeng
 */
public class JsonSystemConfigParser implements ISystemConfigParser {

  @Override
  public RuleConfig parse(String configText) {
    return new RuleConfig().setName("json");
  }
}
