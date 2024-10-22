package com.lzf.module.smsotp.bean;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author leizefeng
 */
@Data
public class SmsOtpSendReq {

  @NotBlank
  private String type;
}
