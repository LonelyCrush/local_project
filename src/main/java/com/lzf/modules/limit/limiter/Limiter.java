package com.lzf.modules.limit.limiter;

import com.lzf.modules.limit.algorithm.LimitAlgorithm;
import com.lzf.modules.limit.rule.ApiLimit;
import com.lzf.modules.limit.rule.LimitRule;
import com.lzf.modules.limit.rule.RuleConfig;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

/**
 * @author leizefeng
 */
public class Limiter {

  private static final Logger LOGGER = LoggerFactory.getLogger(Limiter.class);

  // 为每个api在内存中存储限流计数器
  private final ConcurrentHashMap<String, LimitAlgorithm> limitCountMap = new ConcurrentHashMap<>();

  private final LimitRule limitRule;

  public Limiter() {
    // 将限流规则配置文件limit-rule.yaml中的内容读取到RuleConfig中
    InputStream in = null;
    RuleConfig ruleConfig = null;
    try {
      in = this.getClass().getResourceAsStream("/limit-rule.yaml");
      if (in != null) {
        Yaml yaml = new Yaml();
        ruleConfig = yaml.loadAs(in, RuleConfig.class);
      }
    } finally {
      if (in != null) {
        try {
          in.close();
        } catch (IOException e) {
          LOGGER.error("close file error", e);
        }
      }
    }
    // 将限流规则构建成支持快速查找的数据结构RateLimitRule
    limitRule = new LimitRule(ruleConfig);
  }

  public boolean limit(String appId, String appUrl) {
    ApiLimit apiLimit = limitRule.getLimit(appId, appUrl);
    if (apiLimit == null) {
      return true;
    }

    // 获取api对应在内存中的限流计数器（rateLimitCounter）
    String counterKey = appId + ":" + apiLimit.getApi();
    LimitAlgorithm limitAlgorithm = limitCountMap.get(counterKey);
    if (limitAlgorithm == null) {
      LimitAlgorithm newLimitAlgorithm = new LimitAlgorithm(apiLimit.getLimit());
      limitAlgorithm = limitCountMap.putIfAbsent(counterKey, newLimitAlgorithm);
      if (limitAlgorithm == null) {
        limitAlgorithm = newLimitAlgorithm;
      }
    }

    // 判断是否限流
    return limitAlgorithm.tryAcquire();
  }
}
