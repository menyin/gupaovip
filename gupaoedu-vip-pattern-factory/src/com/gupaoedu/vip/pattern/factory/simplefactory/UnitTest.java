package com.gupaoedu.vip.pattern.factory.simplefactory;

import com.gupaoedu.vip.pattern.factory.pojo.Milk;

public class UnitTest {
    public static void main(String[] args) {
        MilkFactory milkFactory = new MilkFactory();
        Milk milk = milkFactory.getMilk("yili");
        milk.showName();
    }
}
