package com.gupaoedu.vip.distributed.rmi.server;

import com.gupaoedu.vip.distributed.rmi.HelloService;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServerDemo {
    public static void main(String[] args) throws RemoteException {
        try {
            HelloService helloService = new HelloServiceImpl();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://127.0.0.1/Hello", helloService); //注册中心 key - value
            System.out.println("服务启动成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
