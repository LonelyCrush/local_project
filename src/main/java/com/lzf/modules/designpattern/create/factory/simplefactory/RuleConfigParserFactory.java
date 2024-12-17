package com.lzf.modules.designpattern.create.factory.simplefactory;

import com.lzf.modules.designpattern.create.factory.simplefactory.impl.JsonRuleConfigParser;
import com.lzf.modules.designpattern.create.factory.simplefactory.impl.PropertiesRuleConfigParser;
import com.lzf.modules.designpattern.create.factory.simplefactory.impl.XmlRuleConfigParser;
import com.lzf.modules.designpattern.create.factory.simplefactory.impl.YamlRuleConfigParser;
import java.util.HashMap;
import java.util.Map;

/**
 * @author leizefeng
 */
public class RuleConfigParserFactory {

  // 工厂
  private static final Map<String, IRuleConfigParser> cachedParsers = new HashMap<>();

  static {
    cachedParsers.put("json", new JsonRuleConfigParser());
    cachedParsers.put("xml", new XmlRuleConfigParser());
    cachedParsers.put("yaml", new YamlRuleConfigParser());
    cachedParsers.put("properties", new PropertiesRuleConfigParser());
  }

  public static IRuleConfigParser createParser(String configFormat) {
    if (configFormat == null || configFormat.isEmpty()) {
      return null; // 返回null还是IllegalArgumentException全凭你自己说了算
    }
    return cachedParsers.get(configFormat.toLowerCase());
  }

  // 简单工厂，但每次都要创建一个新的parser
//  public static IRuleConfigParser createParser(String configFormat) {
//    if ("json".equalsIgnoreCase(configFormat)) {
//      return new JsonRuleConfigParser();
//    } else if ("xml".equalsIgnoreCase(configFormat)) {
//      return new XmlRuleConfigParser();
//    } else if ("properties".equalsIgnoreCase(configFormat)) {
//      return new PropertiesRuleConfigParser();
//    } else if ("yaml".equalsIgnoreCase(configFormat)) {
//      return new YamlRuleConfigParser();
//    } else {
//      throw new IllegalArgumentException("Unsupported config format: " + configFormat);
//    }
//  }
}
