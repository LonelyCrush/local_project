package com.lzf.local.d240924.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lzf.local.d240924.converter.Converter;
import com.lzf.local.d240924.converter.Resolver;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * @author leizefeng
 */
@Configuration
public class CryptoConfig implements InitializingBean {

  @Autowired
  private RequestMappingHandlerAdapter adapter;

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  public void afterPropertiesSet() throws Exception {
    Converter converter = new Converter(objectMapper);
    List<HttpMessageConverter<?>> messageConverterList = new ArrayList<>();
    messageConverterList.add(converter);
    Resolver resolver = new Resolver(messageConverterList);
    List<HandlerMethodArgumentResolver> argsResolvers =
        new ArrayList<>(Objects.requireNonNull(adapter.getArgumentResolvers()));
    ArrayList<HandlerMethodReturnValueHandler> returnValueHandlers =
        new ArrayList<>(Objects.requireNonNull(adapter.getReturnValueHandlers()));
    argsResolvers.add(0, resolver);
    returnValueHandlers.add(0, resolver);
    adapter.setArgumentResolvers(argsResolvers);
    adapter.setReturnValueHandlers(returnValueHandlers);
  }
}
