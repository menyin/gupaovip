package com.gupaoedu.vip.distributed.myrmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HelloService {
    String sayHello(String msg) ;
}
