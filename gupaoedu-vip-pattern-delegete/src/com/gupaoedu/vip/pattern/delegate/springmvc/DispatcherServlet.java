package com.gupaoedu.vip.pattern.delegate.springmvc;

import com.gupaoedu.vip.pattern.delegate.springmvc.controllers.MemberController;
import com.gupaoedu.vip.pattern.delegate.springmvc.controllers.OrderController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class DispatcherServlet {
    private List<Handler> handlerList = new ArrayList<>();
    public DispatcherServlet() {
        try {
            MemberController memberController = new MemberController();
            handlerList.add(new Handler(memberController, memberController.getClass().getMethod("getMemberName"), memberController.URL));
            OrderController orderController = new OrderController();
            handlerList.add(new Handler(orderController, orderController.getClass().getMethod("getOrderById"), orderController.URL));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    public void doService(HttpServletRequest request, HttpServletResponse response){
        doDispatch(request, response);
    }
    public void doDispatch(HttpServletRequest request, HttpServletResponse response){
        String requestURI = request.getRequestURI();
        Handler currHandler =null;
        for (Handler handler:handlerList) {
            if (handler.getUrl().equals(requestURI)) {
                currHandler = handler;
            }
        }
        try {
            Object result = currHandler.getMethod().invoke(currHandler, request.getParameter("id"));
            response.getWriter().write(request.toString());//响应给浏览器
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class Handler {
        private Object controller;
        private Method method;
        private String url;

        public Handler(Object controller, Method method, String url) {
            this.controller = controller;
            this.method = method;
            this.url = url;
        }

        public Object getController() {
            return controller;
        }

        public void setController(Object controller) {
            this.controller = controller;
        }

        public Method getMethod() {
            return method;
        }

        public void setMethod(Method method) {
            this.method = method;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
