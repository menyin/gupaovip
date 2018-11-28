package com.gupaoedu.vip.pattern.delegate.adapter;

public class UnitTest {
    public static void main(String[] args) {
        WeChatSigninService weChatSigninService = new WeChatSigninService();
        String user12 = weChatSigninService.regist("user12");
    }
}
