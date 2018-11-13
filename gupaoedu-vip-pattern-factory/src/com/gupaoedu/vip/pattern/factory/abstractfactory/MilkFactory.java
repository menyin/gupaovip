package com.gupaoedu.vip.pattern.factory.abstractfactory;

import com.gupaoedu.vip.pattern.factory.pojo.Milk;

public abstract class MilkFactory {
    public abstract Milk getTelunsuMilk();
    public abstract Milk getYiliMilk();
    public abstract Milk getOtherMilk();
}
