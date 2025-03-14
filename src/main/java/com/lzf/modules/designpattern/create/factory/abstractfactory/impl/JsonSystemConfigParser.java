package com.lzf.modules.designpattern.create.factory.abstractfactory.impl;

import com.lzf.modules.designpattern.create.factory.abstractfactory.ISystemConfigParser;
import com.lzf.modules.designpattern.create.factory.simplefactory.RuleConfig;

/**
 * @author leizefeng
 */
public class JsonSystemConfigParser implements ISystemConfigParser {

  @Override
  public RuleConfig parse(String configText) {
    return new RuleConfig().setName("json");
  }
}
