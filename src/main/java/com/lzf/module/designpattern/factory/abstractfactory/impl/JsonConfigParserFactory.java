package com.lzf.module.designpattern.factory.abstractfactory.impl;

import com.lzf.module.designpattern.factory.abstractfactory.IConfigParserFactory;
import com.lzf.module.designpattern.factory.abstractfactory.ISystemConfigParser;
import com.lzf.module.designpattern.factory.simplefactory.IRuleConfigParser;
import com.lzf.module.designpattern.factory.simplefactory.impl.JsonRuleConfigParser;

/**
 * @author leizefeng
 */
public class JsonConfigParserFactory implements IConfigParserFactory {

  @Override
  public IRuleConfigParser createRuleParser() {
    return new JsonRuleConfigParser();
  }

  @Override
  public ISystemConfigParser createSystemParser() {
    return new JsonSystemConfigParser();
  }
}
