package com.gupaoedu.vip.pattern.delegate.springmvc.controllers;

public class MemberController {
    public final static String URL="/getMemberName";
    public String getMemberName(String id){
        System.out.println("会员" + id);
        return "会员"+id;
    }
}
