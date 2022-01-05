package com.gupaoedu.vip.distributed.prestudy.socket.datagramsocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
    public static void main(String[] args) {
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket();


            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String str = null;
            while ((str=bufferedReader.readLine()) != null) {
                if (str =="finish") {
                    break;
                }
                byte[] bytes = str.getBytes();
                InetAddress address = InetAddress.getByName("127.0.0.1");
                DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, address, 5801);
                datagramSocket.send(datagramPacket);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }

    }
}
