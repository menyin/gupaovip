package com.gupaoedu.vip.base.client.mocktest;

import com.gupaoedu.vip.base.server.basetest.Hello;

public class HelloMockImpl implements Hello {
    public String sayHello(String msg) {
        System.out.println("对不起，系统繁忙，请稍等片刻在调用sayHello 时间："+System.currentTimeMillis());
        return "hellMock123";

    }

    public String sayHi(String msg) {
        System.out.println("对不起，系统繁忙，请稍等片刻在调用sayHi 时间："+System.currentTimeMillis());
        return "hiMock123";
    }
}
