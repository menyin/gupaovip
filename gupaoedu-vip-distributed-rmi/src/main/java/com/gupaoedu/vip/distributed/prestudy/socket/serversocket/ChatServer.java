package com.gupaoedu.vip.distributed.prestudy.socket.serversocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[] args) {
        ServerSocket serverSocket=null;
        try {
            serverSocket= new ServerSocket(5801);
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
//            byte[] bytes = new byte[1024];
//            int length = inputStream.read(bytes);
            BufferedReader acceptReader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedReader serverKeyBoard = new BufferedReader(new InputStreamReader(System.in));
            OutputStream outputStream = accept.getOutputStream();
            String lineStr = null;
            while ((lineStr = acceptReader.readLine()) != null) {
                System.out.println("接收到客户端信息："+lineStr);
                String serverMsg = serverKeyBoard.readLine();
                outputStream.write(serverMsg.getBytes());
                System.out.println("我发送了信息："+serverMsg);
            }
            System.out.println(lineStr);
            System.out.println("-----");
//            System.out.println(new String(bytes, 0, length));

//            OutputStream outputStream = accept.getOutputStream();
        } catch (IOException e) {
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
