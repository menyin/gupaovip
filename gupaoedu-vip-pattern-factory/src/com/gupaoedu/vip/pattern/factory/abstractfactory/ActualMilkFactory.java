package com.gupaoedu.vip.pattern.factory.abstractfactory;

import com.gupaoedu.vip.pattern.factory.pojo.Milk;
import com.gupaoedu.vip.pattern.factory.pojo.impl.TelunsuMilk;
import com.gupaoedu.vip.pattern.factory.pojo.impl.YiliMilk;

public class ActualMilkFactory extends MilkFactory {
    @Override
    public Milk getTelunsuMilk() {
        return new TelunsuMilk();
    }

    @Override
    public Milk getYiliMilk() {
        return new YiliMilk();
    }

    @Override
    public Milk getOtherMilk() {
        return new Milk() {
            @Override
            public void showName() {
                System.out.println("杂牌牛奶，谨慎购买");
            }
        };
    }
}
