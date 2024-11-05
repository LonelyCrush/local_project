package com.lzf.module.designpattern.create.factory.abstractfactory.impl;

import com.lzf.module.designpattern.create.factory.abstractfactory.ISystemConfigParser;
import com.lzf.module.designpattern.create.factory.simplefactory.RuleConfig;

/**
 * @author leizefeng
 */
public class JsonSystemConfigParser implements ISystemConfigParser {

  @Override
  public RuleConfig parse(String configText) {
    return new RuleConfig().setName("json");
  }
}
