package com.gupaoedu.vip.spring.v2.webdemo.controller;

import com.gupaoedu.vip.spring.v2.framework.annotation.Autowrire;
import com.gupaoedu.vip.spring.v2.framework.annotation.Controller;
import com.gupaoedu.vip.spring.v2.framework.annotation.Mapping;
import com.gupaoedu.vip.spring.v2.framework.annotation.RequestParam;
import com.gupaoedu.vip.spring.v2.framework.webmvc.ModelAndView;
import com.gupaoedu.vip.spring.v2.webdemo.service.UserService;

import java.util.HashMap;

@Controller
public class TestController {

    @Autowrire
    private UserService userService;
    @Mapping("/test")
    public ModelAndView index(@RequestParam("name") String name,@RequestParam("id") Integer id){
        String idStr = userService.getUserNameById(id.toString());
        String nameStr = userService.getUserNameById(name);
        HashMap<String, Object> model = new HashMap<>();
        model.put("name", nameStr);
        model.put("id", idStr);
        ModelAndView modelAndView = new ModelAndView("test.html", model);
        return modelAndView;
    }
}
