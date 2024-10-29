package com.lzf.module.designpattern.factory.abstractfactory.impl;

import com.lzf.module.designpattern.factory.abstractfactory.IConfigParserFactory;
import com.lzf.module.designpattern.factory.abstractfactory.ISystemConfigParser;
import com.lzf.module.designpattern.factory.simplefactory.IRuleConfigParser;
import com.lzf.module.designpattern.factory.simplefactory.impl.XmlRuleConfigParser;

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
