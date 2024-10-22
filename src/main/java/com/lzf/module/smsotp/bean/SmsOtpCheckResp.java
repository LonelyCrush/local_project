package com.lzf.module.smsotp.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author leizefeng
 */
@Data
@Accessors(chain = true)
public class SmsOtpCheckResp {

  @NotBlank
  private String code;

  @NotNull
  private Integer lastSendOTPTimes;

  @NotNull
  private Integer lastVerifyOTPTimes;
}
