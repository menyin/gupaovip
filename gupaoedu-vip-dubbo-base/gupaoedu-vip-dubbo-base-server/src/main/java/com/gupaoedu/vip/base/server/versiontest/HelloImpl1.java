package com.gupaoedu.vip.base.server.versiontest;

import com.gupaoedu.vip.base.server.basetest.Hello;

public class HelloImpl1 implements Hello{
    public String sayHello(String msg) {
        System.out.println("版本号为0.0.1的sayHello被调用：" + msg);
        return "hello123";
    }

    public String sayHi(String msg) {
        System.out.println("版本号为0.0.1的sayHi被调用：" + msg);
        return "hi123";
    }
}
