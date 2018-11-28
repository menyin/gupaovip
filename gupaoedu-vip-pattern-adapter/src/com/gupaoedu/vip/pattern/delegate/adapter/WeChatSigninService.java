package com.gupaoedu.vip.pattern.delegate.adapter;

public class WeChatSigninService extends SigninService {
    public String regist(String username) {
        String openId="openId-"+username;//通过微信处理成openId
        return super.regist(openId, "");
    }
}
