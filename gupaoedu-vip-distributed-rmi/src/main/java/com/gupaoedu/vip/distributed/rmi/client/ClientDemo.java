package com.gupaoedu.vip.distributed.rmi.client;

import com.gupaoedu.vip.distributed.rmi.HelloService;

import java.rmi.Naming;

public class ClientDemo {
    public static void main(String[] args) {
        try {
            HelloService helloService =
                    (HelloService) Naming.lookup("rmi://127.0.0.1/Hello");
            helloService.sayHello("1111");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
