package com.lzf.modules.designpattern.create.factory.simplefactory;

/**
 * @author leizefeng
 */
public class RuleConfigSource {

  public RuleConfig load(String ruleConfigFilePath) {
    String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
    IRuleConfigParser parser = RuleConfigParserFactory.createParser(ruleConfigFileExtension);
    if (parser == null) {
      throw new InvalidRuleConfigException(
          "Rule config file format is not supported: " + ruleConfigFilePath);
    }

    String configText = "";
    //从ruleConfigFilePath文件中读取配置文本到configText中
    return parser.parse(configText);
  }

  private String getFileExtension(String ruleConfigFilePath) {
    //...解析文件名获取扩展名，比如rule.json，返回json
    return "json";
  }

  public static void main(String[] args) {
    RuleConfigSource source = new RuleConfigSource();
    RuleConfig config = source.load("rule.json");
    System.out.println(config);
  }
}