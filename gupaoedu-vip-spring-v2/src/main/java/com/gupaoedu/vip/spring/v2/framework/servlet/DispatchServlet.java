package com.gupaoedu.vip.spring.v2.framework.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DispatchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



    }

    @Override
    public void init(ServletConfig config) throws ServletException {
       /* //定位
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        //加载
        doScanner(contextConfig.getProperty("scanPackage"));
        //注册
        doRegistry();
        //自动依赖注入
        doAutowired();
        //注册路由处理
        doRouter();
        super.init(config);*/
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
