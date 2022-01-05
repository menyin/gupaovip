package com.gupaoedu.vip.distributed.myrmi.server;

import com.gupaoedu.vip.distributed.myrmi.HelloService;

import java.rmi.RemoteException;

public class HelloServiceImpl implements HelloService {
    public String sayHello(String msg) {
        System.out.println(msg);
        return "服务端sayHello()返回给客户端的结果";
    }
}
