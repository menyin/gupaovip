package com.gupaoedu.vip.pattern.stragy.order;

import com.gupaoedu.vip.pattern.stragy.payment.PayState;
import com.gupaoedu.vip.pattern.stragy.payment.Payment;

import java.math.BigDecimal;

public class Order {
    private String uid;
    private BigDecimal amount;
    private String orderId;

    public Order(String uid, BigDecimal amount, String orderId) {
        this.uid = uid;
        this.amount = amount;
        this.orderId = orderId;
    }
    public PayState pay(Payment payment) {
        return payment.pay(this.uid,this.amount);
    }
}
