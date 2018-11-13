package com.gupaoedu.vip.pattern.factory.simplefactory;

import com.gupaoedu.vip.pattern.factory.pojo.Milk;
import com.gupaoedu.vip.pattern.factory.pojo.impl.TelunsuMilk;
import com.gupaoedu.vip.pattern.factory.pojo.impl.YiliMilk;

public class MilkFactory {
    public  Milk getMilk(String name) {
        if (name.equals("telunsu")) {
            return new TelunsuMilk();
        } else if (name.equals("yili")) {
            return new YiliMilk();
        } else {
            return new Milk() {
                @Override
                public void showName() {
                    System.out.println("未知品牌，请慎喝！！！");
                }
            };
        }

    }
}
