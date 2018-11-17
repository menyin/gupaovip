package com.gupaoedu.vip.pattern.stragy.test;

import com.gupaoedu.vip.pattern.stragy.order.Order;
import com.gupaoedu.vip.pattern.stragy.payment.PayState;
import com.gupaoedu.vip.pattern.stragy.payment.impl.AliPay;

import java.math.BigDecimal;

public class UnitTest {
    public static void main(String[] args) {
        System.out.println("挑选商品添加购物车");
        System.out.println("下单，开始支付");
        Order order = new Order("007", new BigDecimal(998), "2018010112");
        System.out.println("选择支付宝支付");
        PayState pay = order.pay(new AliPay());
        System.out.println(pay);
    }
}
