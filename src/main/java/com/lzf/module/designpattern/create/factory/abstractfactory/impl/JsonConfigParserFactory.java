package com.lzf.module.designpattern.create.factory.abstractfactory.impl;

import com.lzf.module.designpattern.create.factory.abstractfactory.IConfigParserFactory;
import com.lzf.module.designpattern.create.factory.abstractfactory.ISystemConfigParser;
import com.lzf.module.designpattern.create.factory.simplefactory.IRuleConfigParser;
import com.lzf.module.designpattern.create.factory.simplefactory.impl.JsonRuleConfigParser;

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
