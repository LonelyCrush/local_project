package com.lzf.local.d2410.d241029;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author leizefeng
 */
public class HttpCodec {

  ByteBuffer byteBuffer;

  public HttpCodec() {
    byteBuffer = ByteBuffer.allocate(1024 * 10);
  }

  public static void main(String[] args) throws MalformedURLException {
    HttpCodec httpCodec = new HttpCodec();
    httpCodec.testHttp();
  }

  public void testHttp() throws MalformedURLException {
    // 高德地图获取天气api 响应体使用Content-Length
    final HttpUrl url = new HttpUrl(
        "http://restapi.amap.com/v3/weather/weatherInfo?city=上海&key=ccbf1d2515xxxxxx");
    // 快递100查询 api 响应体使用分块编码的api
//    final HttpUrl url = new HttpUrl(
//        "http://www.kuaidi100.com/query?type=shunfeng&postid=8989");
    System.out.println("host：" + url.host);
    System.out.println("protocol：" + url.getProtocol());
    System.out.println("port：" + url.getPort());
    System.out.println("path：" + url.getPath());

    StringBuffer buffer = createRequestPacket(url);
    Socket socket = new Socket();
    try {
      // 通过端口号与指定Host的主机建立了连接
      socket.connect(new InetSocketAddress(url.getHost(), url.getPort()), 5000);

      // 建立了Sockect 连接之后就可以通过对应的方法获取输入输出流，
      // 其中输入流是用于读取服务器的响应数据；而输出流则是客服端发送数据给服务器的
      final OutputStream outputStream = socket.getOutputStream();
      final InputStream inputStream = socket.getInputStream();
      System.out.println("开始发送报文... \n" + buffer);
      sendRequest(buffer, outputStream);
      new Thread(() -> {
        HttpCodec httpCodec = new HttpCodec();
        try {
          // 解析响应行
          String responseLine = httpCodec.readLine(inputStream);
          System.out.println("响应行：" + responseLine);
          System.out.println("响应头：");
          // 解析响应头
          Map<String, String> headers = httpCodec.readHeaders(inputStream);
          for (Map.Entry<String, String> entry : headers.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
          }
          // 解析Content-Length响应体
          if (headers.containsKey("Content-Length")) {
            int length = Integer.parseInt(headers.get("Content-Length"));
            byte[] bytes = httpCodec.readBytes(inputStream, length);
            System.out.println("\n响应体：" + new String(bytes));
          } else {
            // 分块编码
            String response = httpCodec.readChunked(inputStream);
            System.out.println("分块响应体：" + response);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }).start();

      while (true) {
        Thread.sleep(1000 * 10);
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  private StringBuffer createRequestPacket(HttpUrl url) {
    StringBuffer buffer = new StringBuffer();
    // 构造请求行
    buffer.append(HttpConst.GET);
    buffer.append(url.getPath());
    buffer.append(" ");
    buffer.append("HTTP/1.1");
    buffer.append(HttpConst.CRLF);

    // 请求头
    buffer.append(HttpConst.HOST);
    buffer.append(url.getHost());
    buffer.append(HttpConst.CRLF);

    // 请求体，这个请求可以为空所以。。。
    buffer.append(HttpConst.CRLF);
    return buffer;
  }

  private void sendRequest(StringBuffer buffer, OutputStream outputStream) throws IOException {
    outputStream.write(buffer.toString().getBytes());
    outputStream.flush();
  }

  /**
   * 用于解析头部
   *
   * @param inputStream 输入流
   * @return 解析的请求头map
   * @throws IOException io异常
   */
  public Map<String, String> readHeaders(InputStream inputStream) throws IOException {
    HashMap<String, String> headers = new HashMap<>();
    while (true) {
      String line = readLine(inputStream);
      // 读取到空行 则下面的为body
      if (isEmptyLine(line)) {
        break;
      }
      int index = line.indexOf(":");
      if (index > 0) {
        String name = line.substring(0, index);
        // ": "移动两位到总长度减去两个("\r\n")
        String value = line.substring(index + 2, line.length() - 2);
        headers.put(name, value);
      }
    }
    return headers;
  }

  private String readLine(InputStream inputStream) throws IOException {
    try {
      byte b;
      boolean isEndOfLine = false;
      // 标记
      byteBuffer.clear();
      byteBuffer.mark();
      while ((b = (byte) inputStream.read()) != -1) {
        byteBuffer.put(b);
        // 读取到/r则记录，判断下一个字节是否为/n
        if (HttpConst.CR == b) {
          isEndOfLine = true;
        } else if (isEndOfLine) {
          if (HttpConst.LF == b) {
            byte[] lineBytes = new byte[byteBuffer.position()];
            // 返回标记位置
            byteBuffer.reset();
            byteBuffer.get(lineBytes);
            // 清空所有index并重新标记
            byteBuffer.clear();
            byteBuffer.mark();
            return new String(lineBytes);
          }
          isEndOfLine = false;
        }
      }
    } catch (IOException e) {
      throw new IOException(e);
    }
    throw new IOException("Response Read Line.");
  }

  private boolean isEmptyLine(String line) {
    return line == null || line.equals("\r\n");
  }

  /**
   * 解析分块编码形式的响应体
   *
   * @param inputStream 输入流
   * @return 解析的请求头map
   * @throws IOException io异常
   */
  public String readChunked(InputStream inputStream) throws IOException {
    int len = -1;
    boolean isEmptyData = false;
    StringBuilder buffer = new StringBuilder();
    while (true) {
      if (len < 0) {
        String line = readLine(inputStream);
        // 减掉\r\n
        line = line.substring(0, line.length() - 2);
        // Chunked 编码最后一段数据为0 \r\n\r\n
        len = Integer.valueOf(line, 16);
        isEmptyData = len == 0;
      } else {
        // 块的长度不包括\r\n 所以加2 将\r\n读走
        byte[] bytes = readBytes(inputStream, len + 2);
        buffer.append(new String(bytes));
        len = -1;
        if (isEmptyData) {
          return buffer.toString();
        }
      }
    }
  }

  public byte[] readBytes(InputStream inputStream, int length) throws IOException {
    byte[] bytes = new byte[length];
    int readNum = 0;
    while (true) {
      readNum += inputStream.read(bytes, readNum, length - readNum);
      // 读取完毕
      if (readNum == length) {
        return bytes;
      }
    }
  }
}
