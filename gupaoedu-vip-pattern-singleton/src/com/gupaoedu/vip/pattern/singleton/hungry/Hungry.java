package com.gupaoedu.vip.pattern.singleton.hungry;

public class Hungry {
    private Hungry(){}

    private static Hungry hungry = new Hungry();

    public static Hungry getInstance(){
        return hungry;
    }
}
