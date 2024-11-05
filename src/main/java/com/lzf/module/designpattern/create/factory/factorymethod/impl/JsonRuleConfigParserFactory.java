package com.lzf.module.designpattern.create.factory.factorymethod.impl;

import com.lzf.module.designpattern.create.factory.factorymethod.IRuleConfigParserFactory;
import com.lzf.module.designpattern.create.factory.simplefactory.IRuleConfigParser;
import com.lzf.module.designpattern.create.factory.simplefactory.impl.JsonRuleConfigParser;

/**
 * @author leizefeng
 */
public class JsonRuleConfigParserFactory implements IRuleConfigParserFactory {

  @Override
  public IRuleConfigParser createParser() {
    return new JsonRuleConfigParser();
  }
}
