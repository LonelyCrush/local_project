package com.lzf.module.designpattern.create.factory.factorymethod.impl;

import com.lzf.module.designpattern.create.factory.factorymethod.IRuleConfigParserFactory;
import com.lzf.module.designpattern.create.factory.simplefactory.IRuleConfigParser;
import com.lzf.module.designpattern.create.factory.simplefactory.impl.XmlRuleConfigParser;

/**
 * @author leizefeng
 */
public class XmlRuleConfigParserFactory implements IRuleConfigParserFactory {

  @Override
  public IRuleConfigParser createParser() {
    return new XmlRuleConfigParser();
  }
}
