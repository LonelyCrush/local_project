package com.lzf.module.smsotp.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author leizefeng
 */
@Data
public class SmsOtpCheckReq {

  @NotNull
  private Integer version;

  @NotBlank
  private String type;

  @NotBlank
  private String verificationCode;
}
