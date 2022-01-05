package com.gupaoedu.vip.distributed.io.biodemo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServer {
    private static int DEFAULT_PORT = 23456;

    private static ServerSocket serverSocket;
    private final static ExecutorService  executorService = Executors.newCachedThreadPool();
    public static synchronized void start() throws IOException {
        start(DEFAULT_PORT);
    }
    public static synchronized void start(int port) throws IOException {
        if (serverSocket != null) {
            return;
        }
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket accept = serverSocket.accept();
                executorService.execute(new ServerHanlder(accept));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (serverSocket != null) {
                System.out.println("服务已经关闭");
                serverSocket.close();
                serverSocket=null;
            }
        }

    }
}
