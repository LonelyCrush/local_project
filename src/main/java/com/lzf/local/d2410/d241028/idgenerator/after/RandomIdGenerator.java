package com.lzf.local.d2410.d241028.idgenerator.after;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author leizefeng
 */
public class RandomIdGenerator implements LogTraceIdGenerator {

  private static final Logger logger = LoggerFactory.getLogger(RandomIdGenerator.class);

  public static void main(String[] args) {
    RandomIdGenerator generator = new RandomIdGenerator();
    for (int i = 0; i < 10; ++i) {
      System.out.println(generator.generate());
    }
  }

  @Override
  public String generate() {
    String substrOfHostName = getLastfieldOfHostName();
    long currentTimeMillis = System.currentTimeMillis();
    String randomString = generateRandomAlphameric(8);
    String id = String.format("%s-%d-%s",
        substrOfHostName, currentTimeMillis, randomString);
    return id;
  }

  private String getLastfieldOfHostName() {
    String substrOfHostName = null;
    try {
      String hostName = InetAddress.getLocalHost().getHostName();
      String[] tokens = hostName.split("\\.");
      substrOfHostName = tokens[tokens.length - 1];
      return substrOfHostName;
    } catch (UnknownHostException e) {
      logger.warn("Failed to get the host name.", e);
    }
    return substrOfHostName;
  }

  private String generateRandomAlphameric(int length) {
    char[] randomChars = new char[length];
    int count = 0;
    Random random = new Random();
    while (count < length) {
      int maxAscii = 'z';
      int randomAscii = random.nextInt(maxAscii);
      boolean isDigit = randomAscii >= '0' && randomAscii <= '9';
      boolean isUppercase = randomAscii >= 'A' && randomAscii <= 'Z';
      boolean isLowercase = randomAscii >= 'a' && randomAscii <= 'z';
      if (isDigit || isUppercase || isLowercase) {
        randomChars[count] = (char) (randomAscii);
        ++count;
      }
    }
    return new String(randomChars);
  }
}
