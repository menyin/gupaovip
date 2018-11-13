package com.gupaoedu.vip.pattern.factory.pojo.impl;

import com.gupaoedu.vip.pattern.factory.pojo.Milk;

public class YiliMilk implements Milk {
    @Override
    public void showName() {
        System.out.println("伊利牛奶");
    }
}
