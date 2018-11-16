package com.gupaoedu.vip.pattern.proxy.dynamic_jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    public static Object getProxyInstance(Object target) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                     /*   System.out.println(proxy);
                        System.out.println(method);
                        System.out.println(args);*/
                        System.out.println("日志记录begin");
                        Object result = method.invoke(target, args);
                        System.out.println("日志记录end");
                        return result;
                    }
                });
    }
}
