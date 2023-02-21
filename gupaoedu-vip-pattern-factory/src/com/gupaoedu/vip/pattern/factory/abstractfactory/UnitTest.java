package com.gupaoedu.vip.pattern.factory.abstractfactory;

import com.sun.istack.internal.NotNull;

import java.util.concurrent.*;

public class UnitTest {
    public static void main(String[] args) {
        MilkFactory factory = new ActualMilkFactory();
        factory.getTelunsuMilk().showName();
        factory.getYiliMilk().showName();
        factory.getOtherMilk().showName();


    }



}
