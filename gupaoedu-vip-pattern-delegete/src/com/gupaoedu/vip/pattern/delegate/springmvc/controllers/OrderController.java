package com.gupaoedu.vip.pattern.delegate.springmvc.controllers;

public class OrderController {
    public final static String URL="/getOrderById";
    public String getOrderById(String id){
        System.out.println("订单" + id);
        return "订单"+id;
    }

}
