package com.lzf.module.smsotp.controller;

import com.lzf.module.smsotp.bean.SmsOtpCheckReq;
import com.lzf.module.smsotp.bean.SmsOtpCheckResp;
import com.lzf.module.smsotp.bean.SmsOtpSendReq;
import com.lzf.module.smsotp.bean.SmsOtpSendResp;
import com.lzf.module.smsotp.service.ISmsOtpService;
import javax.annotation.Resource;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leizefeng
 */
@RestController
@RequestMapping("/smsotp")
public class SmsOtpController {

  @Resource
  private ISmsOtpService smsOtpService;

  @PostMapping("/send")
  public SmsOtpSendResp send(@RequestBody @Valid SmsOtpSendReq smsOtpSendReq) {
    return smsOtpService.send(smsOtpSendReq);
  }

  @PostMapping("/check")
  public SmsOtpCheckResp check(@RequestBody @Valid SmsOtpCheckReq smsOtpCheckReq) {
    return smsOtpService.check(smsOtpCheckReq);
  }
}
