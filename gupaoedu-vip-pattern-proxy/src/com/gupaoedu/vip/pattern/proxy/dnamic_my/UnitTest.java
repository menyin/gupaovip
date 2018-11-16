package com.gupaoedu.vip.pattern.proxy.dnamic_my;

import java.lang.reflect.Method;

public class UnitTest {
    public static void main(String[] args) throws Exception {
        Son son = new Son();
        Person sonProxy = (Person)MyProxy.newProxyInstance(new MyClassLoader(), son.getClass().getInterfaces(), new MyInvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("日志记录begin");
                Object result = method.invoke(son, args);
                System.out.println("日志记录end");
                return result;
            }
        });

        sonProxy.findLove();
    }
}
