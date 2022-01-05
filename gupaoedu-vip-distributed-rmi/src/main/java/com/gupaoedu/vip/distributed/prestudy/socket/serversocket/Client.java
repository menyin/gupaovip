package com.gupaoedu.vip.distributed.prestudy.socket.serversocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket(InetAddress.getLocalHost(),5801);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("hello jack~".getBytes());
            outputStream.flush();
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int lenght = inputStream.read(bytes);//阻塞的
            System.out.println("客户端接收到信息:"+new String(bytes, 0, lenght));
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
