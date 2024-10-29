package com.lzf.module.designpattern.factory.factorymethod;

import com.lzf.module.designpattern.factory.factorymethod.impl.JsonRuleConfigParserFactory;
import com.lzf.module.designpattern.factory.factorymethod.impl.PropertiesRuleConfigParserFactory;
import com.lzf.module.designpattern.factory.factorymethod.impl.XmlRuleConfigParserFactory;
import com.lzf.module.designpattern.factory.factorymethod.impl.YamlRuleConfigParserFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * @author leizefeng
 */
public class RuleConfigParserFactoryMap {

  // 工厂的工厂
  private static final Map<String, IRuleConfigParserFactory> cachedFactories = new HashMap<>();

  static {
    cachedFactories.put("json", new JsonRuleConfigParserFactory());
    cachedFactories.put("xml", new XmlRuleConfigParserFactory());
    cachedFactories.put("yaml", new YamlRuleConfigParserFactory());
    cachedFactories.put("properties", new PropertiesRuleConfigParserFactory());
  }

  public static IRuleConfigParserFactory getParserFactory(String configFormat) {
    if (configFormat == null || configFormat.isEmpty()) {
      return null;
    }
    return cachedFactories.get(configFormat.toLowerCase());
  }
}
