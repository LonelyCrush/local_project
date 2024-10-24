package com.lzf.local.d2410.d241024;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

/**
 * @author leizefeng
 */
@Slf4j
public class MDCTest {

  public static void main(String[] args) {
    MDC.put("traceId", UUID.randomUUID().toString());
    log.info("MdcTest");
    MDC.clear();
    log.info("clear test");
    MDC.remove("traceId");
  }
}
