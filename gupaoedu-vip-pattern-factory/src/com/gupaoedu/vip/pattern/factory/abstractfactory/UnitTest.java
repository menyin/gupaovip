package com.gupaoedu.vip.pattern.factory.abstractfactory;

public class UnitTest {
    public static void main(String[] args) {
        MilkFactory factory = new ActualMilkFactory();
        factory.getTelunsuMilk().showName();
        factory.getYiliMilk().showName();
        factory.getOtherMilk().showName();
    }
}
