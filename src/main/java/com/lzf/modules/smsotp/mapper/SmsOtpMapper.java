package com.lzf.modules.smsotp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzf.modules.smsotp.mapper.entity.SmsOtp;

/**
 * @author leizefeng
 */
public interface SmsOtpMapper extends BaseMapper<SmsOtp> {

  int getSerialNumber();
}
