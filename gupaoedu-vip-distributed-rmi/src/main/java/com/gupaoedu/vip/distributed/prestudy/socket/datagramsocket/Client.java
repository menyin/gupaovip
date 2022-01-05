package com.gupaoedu.vip.distributed.prestudy.socket.datagramsocket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client {
    public static void main(String[] args) {
        DatagramSocket datagramSocket = null;
        try {
                datagramSocket = new DatagramSocket(5801);
            while (true) {
                byte[] bytes = new byte[1024];
                DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length);
                datagramSocket.receive(datagramPacket);//此方法是阻塞的
                InetAddress address = datagramPacket.getAddress();
                int port = datagramPacket.getPort();
                byte[] data = datagramPacket.getData();
                System.out.println(address+":"+port);
                System.out.println(new String(data, 0, data.length, "utf-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
//            if (datagramSocket != null) {
//                datagramSocket.close();
//            }
        }
    }
}
