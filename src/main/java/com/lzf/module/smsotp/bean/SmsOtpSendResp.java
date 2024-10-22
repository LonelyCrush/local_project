package com.lzf.module.smsotp.bean;


import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author leizefeng
 */
@Data
@Accessors(chain = true)
public class SmsOtpSendResp {

  @NotNull
  private Integer version;

  @NotNull
  private Integer lastSendOtpTimes;
}
