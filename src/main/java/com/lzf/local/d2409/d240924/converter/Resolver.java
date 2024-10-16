package com.lzf.local.d2409.d240924.converter;

import com.lzf.local.d2409.d240924.annotation.Crypto;
import com.lzf.local.d2409.d240924.annotation.Decryption;
import java.util.List;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

/**
 * @author leizefeng
 */
public class Resolver extends RequestResponseBodyMethodProcessor {

  public Resolver(List<HttpMessageConverter<?>> converters) {
    super(converters);
  }

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasMethodAnnotation(Crypto.class)
        && parameter.hasParameterAnnotation(Decryption.class);
  }

  @Override
  public boolean supportsReturnType(MethodParameter returnType) {
    return returnType.hasMethodAnnotation(Crypto.class);
  }
}
