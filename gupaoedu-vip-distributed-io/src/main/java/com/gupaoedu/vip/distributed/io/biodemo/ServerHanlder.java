package com.gupaoedu.vip.distributed.io.biodemo;

import java.io.*;
import java.net.Socket;

public class ServerHanlder implements Runnable {
    private Socket accept;
    public ServerHanlder(Socket accept) {
        this.accept = accept;
    }

    public void run() {
        InputStream inputStream=null;
        InputStreamReader inputStreamReader=null;
        OutputStream outputStream=null;
        try {
            inputStream = accept.getInputStream();
            outputStream = accept.getOutputStream();



            //接收客户端信息
            inputStreamReader = new InputStreamReader(inputStream);
            int len;
            char[] buff = new char[1024];
            while (-1 != (len = inputStreamReader.read(buff))) {
                System.out.println("服务端接收到信息："+new String(buff));
                //响应客户端信息,注意响应代码不能放到while之外，否则会被阻塞
                outputStream.write("欢迎来到永恒国度".getBytes());
            }



        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStreamReader!= null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
