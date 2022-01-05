package com.gupaoedu.vip.distributed.myrmi.client;

import com.gupaoedu.vip.distributed.myrmi.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetAddress;

public class RpcClientProxy implements InvocationHandler{
    private String host;
    private int port;

    public RpcClientProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public <T> T getProxyObj(Class<T> clazz){
        return (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getDeclaringClass()==Object.class){//mybatis源码中学习到的，需要把Object父类的相关方法屏蔽掉
            return method.invoke(this,args);
        }
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();
        System.out.println("++++++"+className+"++++++"+methodName+"-----------------");
        RpcRequest rpcRequest = new RpcRequest(args, className,methodName);
        TcpTransport tcpTransport = new TcpTransport(host, port);
        Object result = tcpTransport.send(rpcRequest);
        return result;
    }
}
