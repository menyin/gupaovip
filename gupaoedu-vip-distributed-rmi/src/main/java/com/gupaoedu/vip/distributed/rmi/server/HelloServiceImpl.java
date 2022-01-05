package com.gupaoedu.vip.distributed.rmi.server;

import com.gupaoedu.vip.distributed.rmi.HelloService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {

    protected HelloServiceImpl() throws RemoteException {
    }


    public void sayHello(String msg) throws RemoteException {
        System.out.println(msg);
    }
}
