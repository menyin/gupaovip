package com.gupaoedu.vip.spring.v2.webdemo.service.impl;

import com.gupaoedu.vip.spring.v2.framework.annotation.Service;
import com.gupaoedu.vip.spring.v2.webdemo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public String getUserNameById(String id) {
        return "hello my name is ：" + id;
    }
}
