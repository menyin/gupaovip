package com.gupaoedu.vip.spring.v1.web.controller;

import com.gupaoedu.vip.spring.v1.framework.servlet.annotation.Autowrire;
import com.gupaoedu.vip.spring.v1.framework.servlet.annotation.Controller;
import com.gupaoedu.vip.spring.v1.framework.servlet.annotation.Mapping;
import com.gupaoedu.vip.spring.v1.web.service.UserService;

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
