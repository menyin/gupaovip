package com.gupaoedu.vip.pattern.factory.pojo.impl;

import com.gupaoedu.vip.pattern.factory.pojo.Milk;

public class TelunsuMilk implements Milk {

    @Override
    public void showName() {
        System.out.println("特仑苏牛奶");
    }
}
