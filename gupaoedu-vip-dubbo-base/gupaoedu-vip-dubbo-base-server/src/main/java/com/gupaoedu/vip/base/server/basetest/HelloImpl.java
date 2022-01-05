package com.gupaoedu.vip.base.server.basetest;

public class HelloImpl implements Hello{
    public String sayHello(String msg) {
        System.out.println("sayHello被调用：" + msg+",服务端时间："+System.currentTimeMillis());
        int i=1/0;
        return "hello123";
    }

    public String sayHi(String msg) {
        System.out.println("sayHi被调用：" + msg);
        return "hi123";
    }
}
