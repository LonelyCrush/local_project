package com.lzf.local.d240924.converter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.lzf.local.d240924.annotation.Decryption;
import com.lzf.local.d240924.annotation.Encryption;
import com.lzf.local.d240924.controller.EncryptResp;
import com.lzf.local.d240924.utils.RSAUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.GenericTypeResolver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StreamUtils;

/**
 * @author leizefeng
 */
@Slf4j
@Component
public class Converter extends MappingJackson2HttpMessageConverter {

  public Converter(ObjectMapper objectMapper) {
    super(objectMapper);
  }

  @Override
  public Object read(
      Type type, @Nullable Class<?> contextClass, final HttpInputMessage inputMessage)
      throws IOException, HttpMessageNotReadableException {
    String body = StreamUtils.copyToString(inputMessage.getBody(), StandardCharsets.UTF_8);
    JsonNode jsonNode = defaultObjectMapper.readValue(body, JsonNode.class);
    log.info("Converter read encrypt request: {}", jsonNode);
    if (jsonNode.isArray()) {
      Iterator<JsonNode> elements = jsonNode.elements();
      while (elements.hasNext()) {
        JsonNode next = elements.next();
        if (next.isObject()) {
          Type actualTypeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
          securityConvert((Class<?>) actualTypeArgument,
              (ObjectNode) next, (short) 0, getParamOrReturnGenericList(type));
        }
      }
    } else {
      ObjectNode objectNode = (ObjectNode) jsonNode;
      JavaType javaType =
          defaultObjectMapper.constructType(GenericTypeResolver.resolveType(type, contextClass));
      securityConvert(javaType.getRawClass(),
          objectNode, (short) 0, getParamOrReturnGenericList(type));
    }
    log.info("Converter read decrypt request: {}", jsonNode);
    HttpInputMessage inputMessageWrapper =
        new HttpInputMessage() {
          @Override
          public InputStream getBody() throws IOException {
            return new ByteArrayInputStream(defaultObjectMapper.writeValueAsBytes(jsonNode));
          }

          @Override
          public HttpHeaders getHeaders() {
            return inputMessage.getHeaders();
          }
        };
    return super.read(type, contextClass, inputMessageWrapper);
  }

  private void securityConvert(
      Class<?> clazz, ObjectNode jsonObject, Short optType, List<Class<?>> list)
      throws IOException {
    Field[] fields = clazz.getDeclaredFields();
    if (fields.length == 0) {
      throw new RuntimeException("fields length is zero");
    }
    for (Field item : fields) {
      if (jsonObject.has(item.getName())) {
        JsonNode itemNode = jsonObject.get(item.getName());
        // 如果是数组节点，需要遍历检查
        if (itemNode.isArray()) {
          List<String> content = new ArrayList<>();
          Iterator<JsonNode> elements = itemNode.elements();
          while (elements.hasNext()) {
            JsonNode next = elements.next();
            // 如果数组中的对象为值，将进行加密和解密转换
            if (next.isValueNode()) {
              if (item.getAnnotation(Decryption.class) != null && optType == 0) {
                content.add(RSAUtils.decrypt(next.textValue()));
              } else if (item.getAnnotation(Encryption.class) != null && optType == 1) {
                content.add(RSAUtils.encrypt(next.textValue()));
              }
              // 如果数组中的对象是引用类型，将进行引用类型的递归处理
            } else {
              ParameterizedType genericType = (ParameterizedType) item.getGenericType();
              Class<?> aClass = null;
              try {
                aClass = (Class<?>) genericType.getActualTypeArguments()[0];
              } catch (ClassCastException e) {
                for (Class<?> item1 : list) {
                  securityConvert(item1, (ObjectNode) next, optType, list);
                }
              }
              if (aClass != null) {
                securityConvert(aClass, (ObjectNode) next, optType, list);
              }
            }
          }
          if (!CollectionUtils.isEmpty(content)) {
            jsonObject.set(item.getName(),
                defaultObjectMapper.readTree(defaultObjectMapper.writeValueAsString(content)));
          }
        }
        // 值类型
        else if (itemNode.isValueNode()) {
          parseJsonValueNode(jsonObject, item, itemNode, optType);
        }
        // 引用类型
        else if (itemNode.isObject()) {
          // 引用类型的递归处理
          securityConvert(item.getType(), (ObjectNode) itemNode, optType, list);
        }
      }
    }
  }

  private List<Class<?>> getParamOrReturnGenericList(Type paramType) {
    List<Class<?>> list = new ArrayList<>();
    if (paramType instanceof ParameterizedType) {
      ParameterizedType parameterizedType = (ParameterizedType) paramType;
      Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
      for (Type actualTypeArgument : actualTypeArguments) {
        list.add((Class<?>) actualTypeArgument);
      }
    }
    return list;
  }


  private void parseJsonValueNode(
      ObjectNode jsonObject, Field field, JsonNode fieldNode, Short optType) {
    // 当字段配置了解密注释并且为读取类型时
    if (field.getAnnotation(Decryption.class) != null && optType == 0) {
      jsonObject.put(field.getName(), RSAUtils.decrypt(fieldNode.textValue()));
    }
    // 当字段配置了加密注释并且为写入类型时
    else if (field.getAnnotation(Encryption.class) != null && optType == 1) {
      jsonObject.put(field.getName(), RSAUtils.encrypt(fieldNode.textValue()));
    }
  }

  @Override
  protected void writeInternal(
      Object object, @Nullable Type type, HttpOutputMessage outputMessage)
      throws IOException, HttpMessageNotWritableException {
    // 仅处理某些格式
    if (object instanceof EncryptResp) {
      EncryptResp retInfo = (EncryptResp) object;
      if (retInfo.getData() == null) {
        return;
      }
      // 仅当内容不为空时加密
      JsonNode jsonNode =
          defaultObjectMapper.readTree(defaultObjectMapper.writeValueAsString(retInfo));
      log.info("Converter writeInternal real response: {}", jsonNode);
      if (jsonNode.isArray()) {
        Iterator<JsonNode> elements = jsonNode.elements();
        while (elements.hasNext()) {
          JsonNode next = elements.next();
          // 如果数组中的对象是引用类型
          if (next.isObject()) {
            if (type != null) {
              Type actualTypeArgument = ((ParameterizedType) type).getActualTypeArguments()[0];
              securityConvert((Class<?>) actualTypeArgument,
                  (ObjectNode) next, (short) 1, getParamOrReturnGenericList(type));
            }
          }
        }
      } else {
        ObjectNode objectNode = (ObjectNode) jsonNode;
        securityConvert(retInfo.getClass(),
            objectNode, (short) 1, getParamOrReturnGenericList(type));
      }
      log.info("Converter writeInternal encrypt response: {}", jsonNode);
      EncryptResp encryptResp = defaultObjectMapper.convertValue(jsonNode, EncryptResp.class);
      retInfo.setData(encryptResp.getData());
    }
    super.writeInternal(object, type, outputMessage);
  }
}
