package com.gupaoedu.vip.pattern.proxy.dynamic_cglib;

import java.lang.reflect.InvocationTargetException;

public class UnitTest {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
       /* Son son = new Son();
        Son sonProxy= (Son) MyProxyFactory.getProxyInstance(son);
        sonProxy.findLove();*/

        Son son = new Son(18);
        Son sonProxy =(Son) new CglibProxy().getCglibProxy(son,new Class[]{int.class},new Object[]{12});
        sonProxy.findLove();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println();
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
