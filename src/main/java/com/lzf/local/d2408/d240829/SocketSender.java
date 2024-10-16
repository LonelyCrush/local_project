package com.lzf.local.d2408.d240829;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * @author leizefeng
 */
public class SocketSender {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    System.out.println("请输入要发送的信息，如需退出或发送完毕请输入\"giao\"：");

    while (true) {
      String message = scanner.next();

      try (DatagramSocket socket = new DatagramSocket()) {
        byte[] messageBytes = message.getBytes();
        DatagramPacket packet = new DatagramPacket(messageBytes, messageBytes.length,
            InetAddress.getByName("localhost"), 8090);
        socket.send(packet);

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
