package com.lzf.module.designpattern.create.factory.factorymethod;

import com.lzf.module.designpattern.create.factory.simplefactory.IRuleConfigParser;
import com.lzf.module.designpattern.create.factory.simplefactory.InvalidRuleConfigException;
import com.lzf.module.designpattern.create.factory.simplefactory.RuleConfig;

/**
 * @author leizefeng
 */
public class RuleConfigSource {

  public RuleConfig load(String ruleConfigFilePath) {
    String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
    IRuleConfigParserFactory parserFactory = RuleConfigParserFactoryMap.getParserFactory(
        ruleConfigFileExtension);
    if (parserFactory == null) {
      throw new InvalidRuleConfigException(
          "Rule config file format is not supported: " + ruleConfigFilePath);
    }
    IRuleConfigParser parser = parserFactory.createParser();

    String configText = "";
    // 从ruleConfigFilePath文件中读取配置文本到configText中
    return parser.parse(configText);
  }

  private String getFileExtension(String ruleConfigFilePath) {
    //...解析文件名获取扩展名，比如rule.json，返回json
    return "yaml";
  }

  public static void main(String[] args) {
    RuleConfigSource source = new RuleConfigSource();
    RuleConfig config = source.load("rule.yaml");
    System.out.println(config);
  }
}