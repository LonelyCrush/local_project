package com.lzf.module.designpattern.create.factory.abstractfactory;

import com.lzf.module.designpattern.create.factory.simplefactory.IRuleConfigParser;

/**
 * @author leizefeng
 */
public interface IConfigParserFactory {

  IRuleConfigParser createRuleParser();

  ISystemConfigParser createSystemParser();
  //此处可以扩展新的parser类型，比如IBizConfigParser

  // 省略YamlConfigParserFactory和PropertiesConfigParserFactory代码
}
