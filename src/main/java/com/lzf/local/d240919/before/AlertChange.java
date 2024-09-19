package com.lzf.local.d240919.before;

import com.lzf.local.d240919.common.AlertRule;
import com.lzf.local.d240919.common.Notification;
import com.lzf.local.d240919.common.NotificationEmergencyLevel;
import lombok.AllArgsConstructor;

/**
 * @author leizefeng
 */
@AllArgsConstructor
public class AlertChange {

  private AlertRule rule;

  private Notification notification;

  // 改动一：添加参数timeoutCount
  // 这意味着调用这个接口的代码都需要做相应的修改
  public void check(String api, long requestCount, long errorCount, long timeoutCount,
      long durationOfSeconds) {
    long tps = requestCount / durationOfSeconds;
    if (tps > rule.getMatchedRule(api).getMaxTps()) {
      notification.notify(NotificationEmergencyLevel.URGENCY, "...");
    }
    if (errorCount > rule.getMatchedRule(api).getMaxErrorCount()) {
      notification.notify(NotificationEmergencyLevel.SEVERE, "...");
    }
    // 改动二：添加接口超时处理逻辑
    // 这意味着相应的单元测试都都需要做相应的修改
    long timeoutTps = timeoutCount / durationOfSeconds;
    if (timeoutTps > rule.getMatchedRule(api).getMaxTimeoutTps()) {
      notification.notify(NotificationEmergencyLevel.URGENCY, "...");
    }
  }
}
