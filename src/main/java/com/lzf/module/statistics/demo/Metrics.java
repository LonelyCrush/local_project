package com.lzf.module.statistics.demo;


import cn.hutool.json.JSONUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author leizefeng
 */
@Component
@Slf4j
public class Metrics {

  // Map的key是接口名称，value对应接口请求的响应时间
  private final Map<String, List<Double>> responseTimeMap = new ConcurrentHashMap<>();

  // 引入定时任务线程池
  private ScheduledExecutorService executor;

  @Value("${scheduled-period}")
  private long period;

  // 记录响应时间
  public void recordResponseTime(String apiName, double responseTime) {
    responseTimeMap.putIfAbsent(apiName, new ArrayList<>());
    responseTimeMap.get(apiName).add(responseTime);
  }

  // 定时播报
  public void repeatedReport() {
    executor = Executors.newSingleThreadScheduledExecutor();
    executor.scheduleAtFixedRate(() -> {
      try {
        Map<String, Map<String, Double>> stateMap = new HashMap<>();
        for (Entry<String, List<Double>> entrySet : responseTimeMap.entrySet()) {
          String apiName = entrySet.getKey();
          List<Double> responseTimeList = entrySet.getValue();
          stateMap.putIfAbsent(apiName, new HashMap<>());
          Double avg = responseTimeList.stream()
              .mapToDouble(Double::doubleValue).average().orElse(Double.NaN);
          stateMap.get(apiName).put("max", Collections.max(responseTimeList));
          stateMap.get(apiName).put("min", Collections.min(responseTimeList));
          stateMap.get(apiName).put("avg", avg);
          stateMap.get(apiName).put("count", (double) responseTimeList.size());
        }
        log.debug(JSONUtil.toJsonStr(stateMap));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }, 0, period, TimeUnit.SECONDS);
  }

  // 关闭定时播报
  public void shutdown() {
    executor.shutdown();
  }

//  @PostConstruct
  public void init() {
    repeatedReport();
  }
}
