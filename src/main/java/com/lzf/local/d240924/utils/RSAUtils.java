package com.lzf.local.d240924.utils;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import javax.crypto.Cipher;
import lombok.extern.slf4j.Slf4j;

/**
 * @author leizefeng
 */
@Slf4j
public class RSAUtils {

  // 签名算法名称
  private static final String RSA_KEY_ALGORITHM = "RSA";

  // 标准签名算法名称
  private static final String RSA_SIGNATURE_ALGORITHM = "SHA1withRSA";
  private static final String RSA2_SIGNATURE_ALGORITHM = "SHA256withRSA";

  // RSA密钥长度，默认密钥长度是1024，密钥长度必须是64的倍数，在512到65536位之间
  // 不管是RSA还是RSA2长度推荐使用2048
  private static final int KEY_SIZE = 2048;

  private static String publicKeyStr;
  private static String privateKeyStr;

  // 工具类中的静态代码块不被自动执行，工具类第一次被调用时静态代码块执行
  static {
    try {
      Map<String, String> keyMap = RSAUtils.generateKey();
      String publicKeyStr = keyMap.get("publicKeyStr");
      String privateKeyStr = keyMap.get("privateKeyStr");
      RSAUtils.publicKeyStr = publicKeyStr;
      RSAUtils.privateKeyStr = privateKeyStr;
      log.debug("RSAUtils Public Key: {}", publicKeyStr);
      log.debug("RSAUtils Private Key: {}", privateKeyStr);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }

  public static String encrypt(String data) {
    try {
      return encryptByPublicKey(data, publicKeyStr);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return data;
  }

  public static String decrypt(String data) {
    try {
      return decryptByPrivateKey(data, privateKeyStr);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return data;
  }

  /**
   * 生成密钥对
   * @return 密钥对map
   */
  private static Map<String, String> generateKey() throws NoSuchAlgorithmException {
    KeyPairGenerator keyPairGenerator;
    keyPairGenerator = KeyPairGenerator.getInstance(RSA_KEY_ALGORITHM);
    SecureRandom secureRandom = new SecureRandom();
    // 初始化随机产生器
    secureRandom.setSeed("Frank".getBytes());
    // 初始化密钥生成器
    Objects.requireNonNull(keyPairGenerator);
    keyPairGenerator.initialize(KEY_SIZE, secureRandom);
    KeyPair keyPair = keyPairGenerator.genKeyPair();
    // 获取公钥并转成Base64编码
    byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
    String publicKeyStr = Base64.getEncoder().encodeToString(publicKeyBytes);
    // 获取私钥并转成Base64编码
    byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
    String privateKeyStr = Base64.getEncoder().encodeToString(privateKeyBytes);
    // 返回
    Map<String, String> keyMap = new HashMap<>();
    keyMap.put("publicKeyStr", publicKeyStr);
    keyMap.put("privateKeyStr", privateKeyStr);
    return keyMap;
  }

  /**
   * 公钥加密（用于数据加密）
   * @param data 加密前的字符串
   * @param publicKeyStr base64编码后的公钥
   * @return base64编码后的字符串
   */
  private static String encryptByPublicKey(String data, String publicKeyStr)
      throws Exception {
    // Java原生Base64解码
    byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyStr);
    // 创建X509编码密钥规范
    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKeyBytes);
    // 返回转换指定算法的KeyFactory对象
    KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
    // 根据X509编码密钥规范产生公钥对象
    PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
    // 根据转换的名称获取密码对象Cipher（转换的名称：算法/工作模式/填充模式）
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    // 用公钥初始化此Cipher对象（加密模式）
    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
    // 对数据加密
    byte[] encryptBytes = cipher.doFinal(data.getBytes());
    // 返回base64编码后的字符串
    return Base64.getEncoder().encodeToString(encryptBytes);
  }

  /**
   * 私钥解密(用于数据解密)
   * @param data 解密前的字符串
   * @param privateKeyStr 私钥
   * @return 解密后的字符串
   */
  private static String decryptByPrivateKey(String data, String privateKeyStr)
      throws Exception {
    // Java原生Base64解码
    byte[] priKey = Base64.getDecoder().decode(privateKeyStr);
    // 创建PKCS8编码密钥规范
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey);
    // 返回转换指定算法的KeyFactory对象
    KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
    // 根据PKCS8编码密钥规范产生私钥对象
    PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
    // 根据转换的名称获取密码对象Cipher（转换的名称：算法/工作模式/填充模式）
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    // 用私钥初始化此Cipher对象（解密模式）
    cipher.init(Cipher.DECRYPT_MODE, privateKey);
    // 对数据解密
    byte[] decrypt = cipher.doFinal(Base64.getDecoder().decode(data));
    // 返回字符串
    return new String(decrypt);
  }

  /**
   * 私钥加密(用于数据签名)
   * @param data 加密前的字符串
   * @param privateKeyStr base64编码后的私钥
   * @return base64编码后后的字符串
   */
  private static String encryptByPrivateKey(String data, String privateKeyStr)
      throws Exception {
    // Java原生base64解码
    byte[] priKey = Base64.getDecoder().decode(privateKeyStr);
    // 创建PKCS8编码密钥规范
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey);
    // 返回转换指定算法的KeyFactory对象
    KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
    // 根据PKCS8编码密钥规范产生私钥对象
    PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
    // 根据转换的名称获取密码对象Cipher（转换的名称：算法/工作模式/填充模式）
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    // 用私钥初始化此Cipher对象（加密模式）
    cipher.init(Cipher.ENCRYPT_MODE, privateKey);
    // 对数据加密
    byte[] encrypt = cipher.doFinal(data.getBytes());
    // 返回base64编码后的字符串
    return Base64.getEncoder().encodeToString(encrypt);
  }

  /**
   * 公钥解密(用于数据验签)
   * @param data 解密前的字符串
   * @param publicKeyStr base64编码后的公钥
   * @return 解密后的字符串
   */
  private static String decryptByPublicKey(String data, String publicKeyStr)
      throws Exception {
    // Java原生base64解码
    byte[] pubKey = Base64.getDecoder().decode(publicKeyStr);
    // 创建X509编码密钥规范
    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKey);
    // 返回转换指定算法的KeyFactory对象
    KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
    // 根据X509编码密钥规范产生公钥对象
    PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
    // 根据转换的名称获取密码对象Cipher（转换的名称：算法/工作模式/填充模式）
    Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
    // 用公钥初始化此Cipher对象（解密模式）
    cipher.init(Cipher.DECRYPT_MODE, publicKey);
    // 对数据解密
    byte[] decrypt = cipher.doFinal(Base64.getDecoder().decode(data));
    // 返回字符串
    return new String(decrypt);
  }

  /**
   * RSA签名
   * @param data 待签名数据
   * @param priKey 私钥
   * @param signType RSA或RSA2
   * @return 签名
   */
  private static String sign(byte[] data, byte[] priKey, String signType)
      throws Exception {
    // 创建PKCS8编码密钥规范
    PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey);
    // 返回转换指定算法的KeyFactory对象
    KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
    // 根据PKCS8编码密钥规范产生私钥对象
    PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
    // 标准签名算法名称(RSA还是RSA2)
    String algorithm = RSA_KEY_ALGORITHM.equals(signType) ?
            RSA_SIGNATURE_ALGORITHM : RSA2_SIGNATURE_ALGORITHM;
    // 用指定算法产生签名对象Signature
    Signature signature = Signature.getInstance(algorithm);
    // 用私钥初始化签名对象Signature
    signature.initSign(privateKey);
    // 将待签名的数据传送给签名对象(须在初始化之后)
    signature.update(data);
    // 返回签名结果字节数组
    byte[] sign = signature.sign();
    // 返回Base64编码后的字符串
    return Base64.getEncoder().encodeToString(sign);
  }

  /**
   * RSA校验数字签名
   *
   * @param data     待校验数据
   * @param sign     数字签名
   * @param pubKey   公钥
   * @param signType RSA或RSA2
   * @return boolean 校验成功返回true，失败返回false
   */
  private static boolean verify(byte[] data, byte[] sign, byte[] pubKey, String signType)
      throws Exception {
    // 返回转换指定算法的KeyFactory对象
    KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
    // 创建X509编码密钥规范
    X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKey);
    // 根据X509编码密钥规范产生公钥对象
    PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
    // 标准签名算法名称(RSA还是RSA2)
    String algorithm = RSA_KEY_ALGORITHM.equals(signType) ?
        RSA_SIGNATURE_ALGORITHM : RSA2_SIGNATURE_ALGORITHM;
    // 用指定算法产生签名对象Signature
    Signature signature = Signature.getInstance(algorithm);
    // 用公钥初始化签名对象,用于验证签名
    signature.initVerify(publicKey);
    // 更新签名内容
    signature.update(data);
    // 得到验证结果
    return signature.verify(sign);
  }

  public static void main(String[] args) throws Exception {
    // 数据的签名和验签
    Map<String, String> keyMap = generateKey();
    String publicKeyStr = keyMap.get("publicKeyStr");
    String privateKeyStr = keyMap.get("privateKeyStr");
    System.out.println("-----------------生成的公钥和私钥------------------------------");
    System.out.println("获取到的公钥：" + publicKeyStr);
    System.out.println("获取到的私钥：" + privateKeyStr);
    // 数字签名
    String data = "tranSeq=1920542585&amount=100&payType=wechat";
    System.out.println("待签名的数据：" + data);
    String sign = sign(data.getBytes(), Base64.getDecoder().decode(privateKeyStr), "RSA");
    System.out.println("数字签名结果：" + sign);
    boolean verify = verify(data.getBytes(), Base64.getDecoder().decode(sign), Base64.getDecoder().decode(publicKeyStr), "RSA");
    System.out.println("数字签名验证结果：" + verify);
  }
}
