package com.lzf.module.designpattern.factory.factorymethod.impl;

import com.lzf.module.designpattern.factory.factorymethod.IRuleConfigParserFactory;
import com.lzf.module.designpattern.factory.simplefactory.IRuleConfigParser;
import com.lzf.module.designpattern.factory.simplefactory.impl.JsonRuleConfigParser;

/**
 * @author leizefeng
 */
public class JsonRuleConfigParserFactory implements IRuleConfigParserFactory {

  @Override
  public IRuleConfigParser createParser() {
    return new JsonRuleConfigParser();
  }
}
