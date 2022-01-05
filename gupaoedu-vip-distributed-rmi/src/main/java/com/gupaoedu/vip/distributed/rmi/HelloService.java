package com.gupaoedu.vip.distributed.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloService extends Remote{
    void sayHello(String msg) throws RemoteException;
}
