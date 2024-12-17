package com.lzf.modules.myproject.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

/**
 * @author leizefeng
 */
@Slf4j
public class RSAUtils {

  public static final int KEY_SIZE = 2048;

  public static final String KEY_PUBLIC_KEY = "publicKey";

  public static final String KEY_PRIVATE_KEY = "privateKey";

  public static final String RSA = "RSA";

  public static Map<String, String> createKeys() {
    return createKeys(KEY_SIZE, KEY_PUBLIC_KEY, KEY_PRIVATE_KEY);
  }

  public static Map<String, String> createKeys(int keySize, String keyPublicKey, String keyPrivateKey) {
    KeyPairGenerator generator = null;
    try {
      generator = KeyPairGenerator.getInstance(RSA);
    } catch (NoSuchAlgorithmException e) {
      log.error(e.getMessage(), e);
    }
    assert generator != null;
    generator.initialize(keySize);
    KeyPair keyPair = generator.generateKeyPair();
    PublicKey publicKey = keyPair.getPublic();
    String valuePublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
    PrivateKey privateKey = keyPair.getPrivate();
    String valuePrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());

    Map<String, String> map = new HashMap<>();
    map.put(keyPublicKey, valuePublicKey);
    map.put(keyPrivateKey, valuePrivateKey);
    return map;
  }

  public static void main(String[] args) {
    Map<String, String> map = createKeys();
    System.out.println(map.get(KEY_PUBLIC_KEY));
    System.out.println(map.get(KEY_PUBLIC_KEY).length());
    System.out.println();
    System.out.println(map.get(KEY_PRIVATE_KEY));
  }
}
