package com.lzf.local.d240829;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.TimeUnit;

/**
 * @author leizefeng
 */
public class SocketReceiver {

  public static void main(String[] args) {

    System.out.println("接收数据中：");

    while (true) {
      try (DatagramSocket socket = new DatagramSocket(8090)) {
        byte[] bytes = new byte[2048];
        DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
        socket.receive(packet);
        String message = new String(packet.getData(), 0, packet.getLength());

        if ("giao".equalsIgnoreCase(message)) {
          TimeUnit.MILLISECONDS.sleep(2000);
          System.out.println("连接已断开！");
          break;
        }

        TimeUnit.MILLISECONDS.sleep(2000);
        System.out.println("主机名：" + packet.getAddress() +  "，发送信息：" + message);
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
