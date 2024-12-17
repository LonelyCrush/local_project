package com.lzf.modules.smsotp.service;

import com.lzf.modules.smsotp.bean.SmsOtpCheckReq;
import com.lzf.modules.smsotp.bean.SmsOtpCheckResp;
import com.lzf.modules.smsotp.bean.SmsOtpSendReq;
import com.lzf.modules.smsotp.bean.SmsOtpSendResp;

/**
 * @author leizefeng
 */
public interface ISmsOtpService {

  SmsOtpSendResp send(SmsOtpSendReq smsOtpSendReq);

  SmsOtpCheckResp check(SmsOtpCheckReq smsOtpCheckReq);
}
