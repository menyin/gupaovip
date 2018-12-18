package com.gupaoedu.vip.spring.v1.framework.servlet.bingding;

import java.lang.reflect.Method;

public class Handler {
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
