package com.lzf.local.d2410.d241029;

import java.net.MalformedURLException;
import java.net.URL;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @author leizefeng
 */
@Data
public class HttpUrl {

  String protocol;

  String host;

  int port;

  String path;

  public HttpUrl(String requestUrl) throws MalformedURLException {
    URL url = new URL(requestUrl);
    protocol = url.getProtocol();
    host = url.getHost();
    port = url.getPort() == -1 ? url.getDefaultPort() : url.getPort();
    path = StringUtils.hasText(url.getPath()) ? url.getFile() : "/";
  }

  public static void main(String[] args) {
    URL url = null;
    try {
      url = new URL(
          "http://restapi.amap.com/v3/weather/weatherInfo?"
              + "city=上海&key=ccbf1d251595efa936df0ba784346902");
    } catch (MalformedURLException e) {
      e.printStackTrace();
    }

    assert url != null;
    System.out.println(url.getProtocol());
    System.out.println(url.getPort());
    System.out.println(url.getDefaultPort());
    System.out.println(url.getHost());
    System.out.println(url.getFile());
    System.out.println(url.getPath());
  }
}
