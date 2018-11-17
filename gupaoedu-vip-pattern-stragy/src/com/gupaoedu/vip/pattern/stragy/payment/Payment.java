package com.gupaoedu.vip.pattern.stragy.payment;

import java.math.BigDecimal;

public interface Payment {
    PayState pay(String uid, BigDecimal amount);
}
