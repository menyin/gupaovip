package com.gupaoedu.vip.distributed.myrmi.server;

import com.gupaoedu.vip.distributed.myrmi.HelloService;

public class ServerDemo {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.publish(helloService,5801);
    }
}
