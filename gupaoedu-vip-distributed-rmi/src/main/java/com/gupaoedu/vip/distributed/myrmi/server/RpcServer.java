package com.gupaoedu.vip.distributed.myrmi.server;

import com.gupaoedu.vip.distributed.myrmi.ProcessorHanlder;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RpcServer {
    public void publish(Object publishService, int port) {
        ServerSocket serverSocket =null;
        try {
            serverSocket = new ServerSocket(port);
            ExecutorService executorService = Executors.newCachedThreadPool();
            while (true) {
                Socket accept = serverSocket.accept();
                executorService.execute(new ProcessorHanlder(accept, publishService));
            }

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
