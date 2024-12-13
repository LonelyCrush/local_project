package com.lzf.local.d2409.d240924.controller;

import com.lzf.local.d2409.d240924.annotation.Encryption;
import lombok.Data;

/**
 * @author leizefeng
 */
@Data
public class EncryptResp {

  @Encryption
  private Object data;
}
