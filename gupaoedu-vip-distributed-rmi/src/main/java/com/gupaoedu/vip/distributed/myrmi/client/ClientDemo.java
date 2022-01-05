package com.gupaoedu.vip.distributed.myrmi.client;

import com.gupaoedu.vip.distributed.myrmi.HelloService;

import java.net.InetAddress;
import java.rmi.RemoteException;

public class ClientDemo {
    public static void main(String[] args) {

        try {
            HelloService helloService = new RpcClientProxy(
                   "127.0.0.1",
                    5801).getProxyObj(HelloService.class);
            String result = helloService.sayHello("客户端调用服务端sayHello方法");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
