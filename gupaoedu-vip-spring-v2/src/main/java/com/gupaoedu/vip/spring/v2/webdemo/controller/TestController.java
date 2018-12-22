package com.gupaoedu.vip.spring.v2.webdemo.controller;

import com.gupaoedu.vip.spring.v2.framework.annotation.Autowrire;
import com.gupaoedu.vip.spring.v2.framework.annotation.Controller;
import com.gupaoedu.vip.spring.v2.framework.annotation.Mapping;
import com.gupaoedu.vip.spring.v1.webdemo.service.UserService;

@Controller
public class TestController {

    @Autowrire
    private UserService userService;
    @Mapping("/index")
    public String index(){
        String xiaoqiang = userService.getUserNameById("Jack");
        return xiaoqiang;
    }
}
