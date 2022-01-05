package com.gupaoedu.vip.distributed.prestudy.socket.serversocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(5801);
            Socket accept = serverSocket.accept();//此方法是阻塞的
            InputStream inputStream = accept.getInputStream();
            byte[] bytes = new byte[1024];

            int lenth = inputStream.read(bytes);//被阻塞
            System.out.println("服务端接收到信息："+new String(bytes, 0, lenth));
//            byte[] outBytes=new byte[1024];
            OutputStream outputStream = accept.getOutputStream();
            outputStream.write("你好客户端~".getBytes());
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
