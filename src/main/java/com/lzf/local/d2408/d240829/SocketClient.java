package com.lzf.local.d2408.d240829;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author leizefeng
 */
public class SocketClient {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    System.out.println("请输入要发送的信息，如需退出或发送完毕请输入\"giao\"：");

    while (true) {
      String message = scanner.next();

      try (Socket socket = new Socket("localhost", 8091);
          OutputStream outputStream = socket.getOutputStream()
      ) {
        outputStream.write(message.getBytes());

        if ("giao".equalsIgnoreCase(message)) {
          System.out.println("正在退出...");
          break;
        }

        System.out.println("发送成功！");
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
