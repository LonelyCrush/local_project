package com.lzf.module.smsotp.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lzf.module.smsotp.bean.SmsOtpCheckReq;
import com.lzf.module.smsotp.bean.SmsOtpCheckResp;
import com.lzf.module.smsotp.bean.SmsOtpSendReq;
import com.lzf.module.smsotp.bean.SmsOtpSendResp;
import com.lzf.module.smsotp.mapper.SmsOtpMapper;
import com.lzf.module.smsotp.mapper.entity.SmsOtp;
import com.lzf.module.smsotp.service.ISmsOtpService;
import java.time.LocalDateTime;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author leizefeng
 */
@Service
@Slf4j
public class SmsOtpServiceImpl implements ISmsOtpService {

  @Resource
  private SmsOtpMapper smsOtpMapper;

  @Value("${smsotp.initialValue.version}")
  private Integer initVersion;

  @Value("${smsotp.initialValue.lastSendOtpTimes}")
  private Integer initLastSendOtpTimes;

  @Value("${smsotp.initialValue.lastVerifyOtpTimes}")
  private Integer initLastVerifyOtpTimes;

  @Value("${smsotp.initialValue.codeLength}")
  private Integer initCodeLength;

  @Value("${smsotp.maximum.lastSendOtpTimes}")
  private Integer maxLastSendOtpTimes;

  @Value("${smsotp.maximum.lastVerifyOtpTimes}")
  private Integer maxLastVerifyOtpTimes;

  @Value("${smsotp.interval.sendFreezeTime}")
  private long sendFreezeTime;

  @Value("${smsotp.interval.sendIntervalTime}")
  private long sendIntervalTime;

  @Override
  public SmsOtpSendResp send(SmsOtpSendReq smsOtpSendReq) {
    // get encCrn and type
    String encCrn = "Tp1PSSQkcU89JcyvJUnEfvmjILE1P+4ecw==";
    String type = smsOtpSendReq.getType();

    // select smsOtp
    SmsOtp smsOtp = selectSmsOtp(encCrn, type);

    String verificationCode = RandomUtil.randomNumbers(initCodeLength);
    if (smsOtp == null) {
      // insert smsOtp
      insertSmsOtp(encCrn, type, verificationCode);
      // send smsOtp
      sendSmsOtp(verificationCode);
      // return
      SmsOtpSendResp smsOtpSendResp = new SmsOtpSendResp();
      return smsOtpSendResp.setVersion(initVersion).setLastSendOtpTimes(initLastSendOtpTimes);
    }

    // check
    checkTimes(smsOtp);
    // update smsOtp
    updateSmsOtp(verificationCode, smsOtp);
    // send smsOtp
    sendSmsOtp(verificationCode);
    // return
    SmsOtpSendResp smsOtpSendResp = new SmsOtpSendResp();
    smsOtpSendResp
        .setVersion(smsOtp.getVersion())
        .setLastSendOtpTimes(smsOtp.getLastSendOtpTimes());
    return smsOtpSendResp;
  }

  private SmsOtp selectSmsOtp(String encCrn, String type) {
//    LambdaQueryWrapper<SmsOtp> wrapper = new LambdaQueryWrapper<>();
//    wrapper.eq(SmsOtp::getEncCrn, encCrn).eq(SmsOtp::getType, type);
//    return smsOtpMapper.selectOne(wrapper);
//    return null;
    SmsOtp smsOtp = new SmsOtp();
    smsOtp
        .setId(156)
        .setVersion(0)
        .setType("type")
        .setLastSendOtpTimes(0)
        .setLastVerifyOtpTimes(0)
        .setVerificationCode("123456")
        .setEncCrn("Tp1PSSQkcU89JcyvJUnEfvmjILE1P+4ecw==")
        .setSendTime(LocalDateTime.of(2024, 10, 22,
            17, 7, 0));
    return smsOtp;
  }

  private void insertSmsOtp(String encCrn, String type, String verificationCode) {
//    SmsOtp smsOtp = new SmsOtp();
//    smsOtp
//        .setVersion(initVersion)
//        .setType(type)
//        .setLastSendOtpTimes(initLastSendOtpTimes)
//        .setLastVerifyOtpTimes(initLastVerifyOtpTimes)
//        .setVerificationCode(verificationCode)
//        .setEncCrn(encCrn)
//        .setSendTime(LocalDateTime.now());
//    if (smsOtpMapper.insert(smsOtp) != 1) {
//      throw new RuntimeException("insert smsOtp failed");
//    }
  }

  private void sendSmsOtp(String verificationCode) {
    log.info("Sending smsOtp");
    // create serialNumber
//    int serialNumber = smsOtpMapper.getSerialNumber();
    int serialNumber = 1;
    String formatSerialNumber = String.format("%06d", serialNumber);
    log.info("Serial number: {}", formatSerialNumber);
    log.info("Sms code: {}", verificationCode);
    log.info("Sending smsOtp success");
  }

  private void checkTimes(SmsOtp smsOtp) {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime sendTime = smsOtp.getSendTime();
    // initial Times
    if (now.isAfter(sendTime.plusHours(sendFreezeTime))) {
      smsOtp
          .setLastSendOtpTimes(initLastSendOtpTimes)
          .setLastVerifyOtpTimes(initLastVerifyOtpTimes);
    } else if (smsOtp.getLastSendOtpTimes() < maxLastSendOtpTimes) {
      smsOtp
          .setLastSendOtpTimes(smsOtp.getLastSendOtpTimes() + 1)
          .setLastVerifyOtpTimes(initLastVerifyOtpTimes);
    } else {
      throw new RuntimeException("Resending too often, please try again later");
    }
  }

  private void updateSmsOtp(String verificationCode, SmsOtp smsOtp) {
    smsOtp
        .setVersion(smsOtp.getVersion() + 1);
//    smsOtp
//        .setVersion(smsOtp.getVersion() + 1)
//        .setVerificationCode(verificationCode)
//        .setSendTime(LocalDateTime.now());
//    LambdaQueryWrapper<SmsOtp> wrapper = new LambdaQueryWrapper<>();
//    wrapper
//        .eq(SmsOtp::getEncCrn, smsOtp.getEncCrn())
//        .eq(SmsOtp::getType, smsOtp.getType());
//    if (smsOtpMapper.update(smsOtp, wrapper) != 1) {
//      throw new RuntimeException("update smsOtp failed");
//    }
  }

  @Override
  public SmsOtpCheckResp check(SmsOtpCheckReq smsOtpCheckReq) {
    // get encCrn and type and version
    String encCrn = "Tp1PSSQkcU89JcyvJUnEfvmjILE1P+4ecw==";
    String type = smsOtpCheckReq.getType();
    Integer version = smsOtpCheckReq.getVersion();

    // select smsOtp
    SmsOtp smsOtp = selectSmsOtp(encCrn, type, version);
    if (smsOtp == null) {
      throw new RuntimeException("Invalid version");
    }
    if (LocalDateTime.now().isAfter(smsOtp.getSendTime().plusSeconds(sendIntervalTime))) {
      throw new RuntimeException("Verification code expired");
    }
    SmsOtpCheckResp smsOtpCheckResp = new SmsOtpCheckResp();
    if (!smsOtp.getVerificationCode().equals(smsOtpCheckReq.getVerificationCode())) {
      smsOtpCheckResp
          .setCode("0001")
          .setLastSendOTPTimes(smsOtp.getLastSendOtpTimes())
          .setLastVerifyOTPTimes(smsOtp.getLastVerifyOtpTimes() + 1);
      return smsOtpCheckResp;
    }
    // delete smsOtp
    deleteSmsOtp(encCrn, type, version);
    return smsOtpCheckResp.setCode("0000");
  }

  private SmsOtp selectSmsOtp(String encCrn, String type, Integer version) {
//    LambdaQueryWrapper<SmsOtp> wrapper = new LambdaQueryWrapper<>();
//    wrapper
//        .eq(SmsOtp::getEncCrn, encCrn)
//        .eq(SmsOtp::getType, type)
//        .eq(SmsOtp::getVersion, version);
//    return smsOtpMapper.selectOne(wrapper);
//    return null;
    return selectSmsOtp(encCrn, type);
  }

  private void deleteSmsOtp(String encCrn, String type, Integer version) {
    LambdaQueryWrapper<SmsOtp> wrapper = new LambdaQueryWrapper<>();
    wrapper
       .eq(SmsOtp::getEncCrn, encCrn)
       .eq(SmsOtp::getType, type)
       .eq(SmsOtp::getVersion, version);
    if (smsOtpMapper.delete(wrapper) != 1) {
      throw new RuntimeException("delete smsOtp failed");
    }
  }
}
