package com.gupaoedu.vip.spring.v2.framework.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AopProxy implements InvocationHandler {

    private Object target;
    private AopConfig aopConfig;

    public Object getProxy(Object instance) {
       this.target = instance;
       return Proxy.newProxyInstance(instance.getClass().getClassLoader(), instance.getClass().getInterfaces(), this);
    }
    public void setConfig(AopConfig aopConfig){
        this.aopConfig = aopConfig;

    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //根据aopConfig配置的信息进行拦截
        System.out.println("根据aopConfig配置的信息进行拦截");
        Object invoke = method.invoke(target, args);
        return invoke;
    }

}
