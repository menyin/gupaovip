package com.gupaoedu.vip.distributed.io.biodemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class BioClient {
    private static int DEFAULT_PORT=23456;

    public static void send() throws IOException {
        send(DEFAULT_PORT);
    }
    public static void send(int port) throws IOException {
        Socket socket =null;
        OutputStream outputStream =null;
        InputStream inputStream=null;
        try {
            socket = new Socket("127.0.0.1", port);
            outputStream = socket.getOutputStream();
            outputStream.write("你好，服务端".getBytes());
            inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            int len;
            char[] buff = new char[1024];
            while ((len=inputStreamReader.read(buff))!=-1){
                System.out.println("客户端接收信息："+new String(buff));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (socket != null) {
                socket.close();
                socket=null;
            }

        }

    }
}
