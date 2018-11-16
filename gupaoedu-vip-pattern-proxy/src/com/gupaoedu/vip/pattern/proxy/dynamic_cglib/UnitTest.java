package com.gupaoedu.vip.pattern.proxy.dynamic_cglib;

public class UnitTest {
    public static void main(String[] args) {
        Son son = new Son();
        Son sonProxy= (Son) MyProxyFactory.getProxyInstance(son);
        sonProxy.findLove();
    }
}
