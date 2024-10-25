package com.lzf.local.d2410.d241025.config;

import java.util.Map;
import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.stereotype.Component;

/**
 * @author leizefeng
 */
@Component
public class CommonDecorator implements TaskDecorator {

  @Override
  public Runnable decorate(Runnable runnable) {
    Map<String, String> mdcMap = MDC.getCopyOfContextMap();
    Map<String, Object> asyncMap = AsyncUtil.deepCopyMap(AsyncUtil.getAsyncMap());
    return () -> {
      try {
        MDC.setContextMap(mdcMap);
        AsyncUtil.setAsyncMap(asyncMap);
        runnable.run();
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        MDC.clear();
        AsyncUtil.commonThreadLocal.remove();
      }
    };
  }
}
