package com.lzf.modules.limit.rule;

import java.util.List;
import lombok.Data;

/**
 * @author leizefeng
 */
@Data
public class RuleConfig {

  private List<AppRuleConfig> configs;

  @Data
  private static class AppRuleConfig {

    private String appId;

    private List<ApiLimit> apiLimitList;
  }
}
