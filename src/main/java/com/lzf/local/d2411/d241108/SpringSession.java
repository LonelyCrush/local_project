package com.lzf.local.d2411.d241108;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author leizefeng
 */
@TableName("SPRING_SESSION")
@Data
public class SpringSession {

  private static final long serialVersionUID = 1L;

  /** primary id */
  private String primaryId;

  /** sessionId */
  private String sessionId;

  /** creationTime */
  private Long creationTime;

  /** lastAccessTime */
  private Long lastAccessTime;

  /** maxInactiveInterval */
  private Integer maxInactiveInterval;

  /** expiryTime */
  private Long expiryTime;

  /** principalName */
  private String principalName;
}
