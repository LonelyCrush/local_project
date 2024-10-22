package com.lzf.module.smsotp.service;

import com.lzf.module.smsotp.bean.SmsOtpCheckReq;
import com.lzf.module.smsotp.bean.SmsOtpCheckResp;
import com.lzf.module.smsotp.bean.SmsOtpSendReq;
import com.lzf.module.smsotp.bean.SmsOtpSendResp;

/**
 * @author leizefeng
 */
public interface ISmsOtpService {

  SmsOtpSendResp send(SmsOtpSendReq smsOtpSendReq);

  SmsOtpCheckResp check(SmsOtpCheckReq smsOtpCheckReq);
}
