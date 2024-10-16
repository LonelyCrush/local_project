package com.lzf.local.d2409.d240924.controller;

import com.lzf.local.d2409.d240924.utils.RSAUtils;
import com.lzf.local.d2409.d240924.annotation.Crypto;
import com.lzf.local.d2409.d240924.annotation.Decryption;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leizefeng
 */
@RestController
@RequestMapping("/d240924")
public class TestController {

  @PostMapping("/encryptByUtils")
  public String encryptByUtils() {
    return RSAUtils.encrypt("Hello World!");
  }

  @PostMapping("/decryptByUtils")
  public String decryptByUtils(@RequestBody DecryptReq decryptReq) {
    return RSAUtils.decrypt(decryptReq.getData());
  }

  @Crypto
  @PostMapping("/encrypt")
  public EncryptResp encrypt() {
    EncryptResp encryptResp = new EncryptResp();
    encryptResp.setData("Hello World");
    return encryptResp;
  }

  @Crypto
  @PostMapping("/decrypt")
  public String decrypt(@RequestBody @Decryption DecryptReq decryptReq) {
    return decryptReq.getData();
  }
}
