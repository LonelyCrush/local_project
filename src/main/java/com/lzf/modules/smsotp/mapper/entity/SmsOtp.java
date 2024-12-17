package com.lzf.modules.smsotp.mapper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author leizefeng
 */
@TableName("TB_SMS_OTP_A")
@Data
@Accessors(chain = true)
public class SmsOtp {

  @TableId(type = IdType.AUTO)
  private Integer id;

  private Integer version;

  private String type;

  private Integer lastSendOtpTimes;

  private Integer lastVerifyOtpTimes;

  private String verificationCode;

  private String encCrn;

  private LocalDateTime sendTime;
}
