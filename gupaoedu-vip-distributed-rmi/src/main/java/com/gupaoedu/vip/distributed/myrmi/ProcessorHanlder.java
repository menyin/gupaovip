package com.gupaoedu.vip.distributed.myrmi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProcessorHanlder implements Runnable {
    private Socket acceptSocket;
    private Object service;
//    private int port;

    public ProcessorHanlder(Socket acceptSocket, Object service) {
        this.acceptSocket = acceptSocket;
        this.service = service;
    }

    public void run() {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(acceptSocket.getInputStream());
                RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
                Object result = invokeMethod(rpcRequest);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(acceptSocket.getOutputStream());
                objectOutputStream.writeObject(result);

            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    acceptSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    private Object invokeMethod(RpcRequest rpcRequest) {
        String className = rpcRequest.getClassName();//rmi中会根据className到注册器里找到对应的实现实例service
        String methodName = rpcRequest.getMethodName();
        Object[] parameters = rpcRequest.getParameters();
        Class[] paramsTypes = new Class[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            paramsTypes[i] = parameters[i].getClass();
        }
        try {
            Method method = service.getClass().getMethod(methodName,paramsTypes );
            Object invoke = method.invoke(service, parameters);
            return invoke;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
