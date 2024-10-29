package com.lzf.module.designpattern.factory.factorymethod.impl;

import com.lzf.module.designpattern.factory.factorymethod.IRuleConfigParserFactory;
import com.lzf.module.designpattern.factory.simplefactory.IRuleConfigParser;
import com.lzf.module.designpattern.factory.simplefactory.impl.XmlRuleConfigParser;

/**
 * @author leizefeng
 */
public class XmlRuleConfigParserFactory implements IRuleConfigParserFactory {

  @Override
  public IRuleConfigParser createParser() {
    return new XmlRuleConfigParser();
  }
}
