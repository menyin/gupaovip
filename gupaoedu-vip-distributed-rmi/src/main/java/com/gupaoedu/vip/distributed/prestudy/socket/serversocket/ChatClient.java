package com.gupaoedu.vip.distributed.prestudy.socket.serversocket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket(InetAddress.getLocalHost(), 5801);
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            String clientMsg=null;
            while ((clientMsg = bufferedReader.readLine()) != null) {
                outputStream.write((clientMsg+"\r\n").getBytes());
                System.out.println("我发送了信息："+clientMsg + "\r\n");
                int length = inputStream.read(bytes);
                System.out.println("接收到服务端信息："+new String(bytes, 0, length)+"\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
