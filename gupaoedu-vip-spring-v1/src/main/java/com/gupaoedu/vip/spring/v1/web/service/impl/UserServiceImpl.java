package com.gupaoedu.vip.spring.v1.web.service.impl;

import com.gupaoedu.vip.spring.v1.framework.servlet.annotation.Service;
import com.gupaoedu.vip.spring.v1.web.service.UserService;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public String getUserNameById(String id) {
        return "hello my name is ：" + id;
    }
}
