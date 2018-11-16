package com.gupaoedu.vip.pattern.proxy.staticed;

public class UnitTest {
    public static void main(String[] args) {
        Son son = new Son();
        Father father = new Father(son);
        father.findLove();
        father.findJob();
    }
}
