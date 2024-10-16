package com.lzf.local.d2408.d240829;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.concurrent.TimeUnit;

/**
 * @author leizefeng
 */
public class SocketServer {

  public static void main(String[] args) {

    System.out.println("接收数据中：");

    while (true) {
      try (ServerSocket socket = new ServerSocket(8091);
          InputStream inputStream = socket.accept().getInputStream()
      ) {
        byte[] bytes = new byte[2048];
        String message = new String(bytes, 0, inputStream.read(bytes));

        if ("giao".equalsIgnoreCase(message)) {
          System.out.println("连接已断开！");
          break;
        }

        TimeUnit.MILLISECONDS.sleep(2000);
        System.out.println("主机名：" + socket.accept().getInetAddress() +  "，发送信息：" + message);
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
