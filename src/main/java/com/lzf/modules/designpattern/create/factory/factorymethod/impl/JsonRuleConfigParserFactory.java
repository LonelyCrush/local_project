package com.lzf.modules.designpattern.create.factory.factorymethod.impl;

import com.lzf.modules.designpattern.create.factory.factorymethod.IRuleConfigParserFactory;
import com.lzf.modules.designpattern.create.factory.simplefactory.IRuleConfigParser;
import com.lzf.modules.designpattern.create.factory.simplefactory.impl.JsonRuleConfigParser;

/**
 * @author leizefeng
 */
public class JsonRuleConfigParserFactory implements IRuleConfigParserFactory {

  @Override
  public IRuleConfigParser createParser() {
    return new JsonRuleConfigParser();
  }
}
