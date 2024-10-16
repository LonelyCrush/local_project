package com.lzf.local.d2409.d240924.controller;

import com.lzf.local.d2409.d240924.annotation.Decryption;
import lombok.Data;

/**
 * @author leizefeng
 */
@Data
public class DecryptReq {

  @Decryption
  private String data;
}
