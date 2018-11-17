package com.gupaoedu.vip.pattern.stragy.payment.impl;

import com.gupaoedu.vip.pattern.stragy.payment.PayState;
import com.gupaoedu.vip.pattern.stragy.payment.Payment;

import java.math.BigDecimal;

public class AliPay implements Payment {
    @Override
    public PayState pay(String uid, BigDecimal amount) {
        System.out.println("调取支付宝接口");
        System.out.println("如果支付成功，则返回正确信息");
        PayState payState = new PayState(200,"支付宝支付成功","{orderid:'1231231',amount:198}");
        return payState;
    }
}
