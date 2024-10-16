package com.lzf.local.d2409.d240913;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author leizefeng
 */
@Configuration
public class JsonRespConfiguration implements WebMvcConfigurer {

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(mappingJackson2HttpMessageConverter());
  }

  @Bean
  public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
    // 通过该方法对mapper对象进行设置，所有序列化的对象都将按改规则进行系列化
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

    ObjectMapper objectMapper = new ObjectMapper();
    // 允许有未知字段映射不上
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    // 过滤response的空值
    // Include.ALWAYS 默认
    // Include.NON_DEFAULT 属性为默认值不序列化
    // Include.NON_EMPTY 属性为 空（""） 或者为 NULL 都不序列化，则返回的json是没有这个字段的。
    // Include.NON_NULL 属性为NULL 不序列化，就是为null的字段不参加序列化
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    converter.setDefaultCharset(StandardCharsets.UTF_8);
    converter.setObjectMapper(objectMapper);
    return converter;
  }
}
