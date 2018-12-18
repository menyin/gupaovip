package com.gupaoedu.vip.spring.v1.webdemo.service.impl;

import com.gupaoedu.vip.spring.v1.framework.annotation.Service;
import com.gupaoedu.vip.spring.v1.webdemo.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public String getUserNameById(String id) {
        return "hello my name is ：" + id;
    }
}
