package com.lzf.module.myproject.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author leizefeng
 */
public class WebUtils {

  // 使用 Spring Framework 提供的多种请求属性接口
  // 如 RequestAttributes、NativeWebRequest 和 ServletRequestAttributes
  // 来兼容不同的请求处理场景，确保在不同的环境中都能正确地获取到 HTTP 请求对象。
  // 仅使用 NativeWebRequest 是完全可行的，特别是当你的应用只在 Servlet 环境中运行时。
  // 然而，使用 ServletRequestAttributes 作为补充可以提供更多的便利，
  // 尤其是在处理特定于 Servlet 的请求时。
  public static HttpServletRequest getHttpRequest() {
    HttpServletRequest request = null;

    RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
    if (requestAttributes instanceof NativeWebRequest) {
      NativeWebRequest nativeWebRequest = (NativeWebRequest) requestAttributes;
      request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
    } else if (requestAttributes instanceof ServletRequestAttributes) {
      ServletRequestAttributes servletRequestAttributes =
          (ServletRequestAttributes) requestAttributes;
      request = servletRequestAttributes.getRequest();
    }

    return request;
  }

  public static HttpServletResponse getHttpResponse() {
    HttpServletResponse response = null;

    RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
    if (requestAttributes instanceof NativeWebRequest) {
      NativeWebRequest nativeWebRequest = (NativeWebRequest) requestAttributes;
      response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
    } else if (requestAttributes instanceof ServletRequestAttributes) {
      ServletRequestAttributes servletRequestAttributes =
          (ServletRequestAttributes) requestAttributes;
      response = servletRequestAttributes.getResponse();
    }

    return response;
  }
}
