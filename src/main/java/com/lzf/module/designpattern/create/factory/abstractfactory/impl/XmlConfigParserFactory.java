package com.lzf.module.designpattern.create.factory.abstractfactory.impl;

import com.lzf.module.designpattern.create.factory.abstractfactory.IConfigParserFactory;
import com.lzf.module.designpattern.create.factory.abstractfactory.ISystemConfigParser;
import com.lzf.module.designpattern.create.factory.simplefactory.IRuleConfigParser;
import com.lzf.module.designpattern.create.factory.simplefactory.impl.XmlRuleConfigParser;

/**
 * @author leizefeng
 */
public class XmlConfigParserFactory implements IConfigParserFactory {

  @Override
  public IRuleConfigParser createRuleParser() {
    return new XmlRuleConfigParser();
  }

  @Override
  public ISystemConfigParser createSystemParser() {
  return new XmlSystemConfigParser();
  }
}
