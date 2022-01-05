package com.gupaoedu.vip.spring.v2.framework.webmvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;

public class HandlerAdaptor {
    private Map<String,Integer> paramMapping;

    public HandlerAdaptor(Map<String, Integer> paramMapping) {
        this.paramMapping = paramMapping;
    }

    public ModelAndView handle(HttpServletRequest req, HttpServletResponse resp, HandlerMapping handlerMapping) throws InvocationTargetException, IllegalAccessException {
        Method method = handlerMapping.getMethod();
        Object[] args = new Object[method.getParameters().length];
        for (Map.Entry<String, Integer> stringIntegerEntry : this.paramMapping.entrySet()) {
            Integer index = stringIntegerEntry.getValue();
            String name = stringIntegerEntry.getKey();
            if(name.equals("javax.servlet.http.HttpServletRequest")){
                args[index]=req;
                continue;
            }
            if(name.equals("javax.servlet.http.HttpServletResponse")){
                args[index]=resp;
                continue;
            }

            String paramStrVal = req.getParameter(name);
            Class<?> aClass = handlerMapping.getMethod().getParameterTypes()[index];
            Object valObj=caseString2Value(paramStrVal,aClass);
            args[index] = valObj;
        }
        Object invoke = method.invoke(handlerMapping.getController(), args);
        if(invoke!=null&&invoke.getClass()==ModelAndView.class){
            return (ModelAndView) invoke;
        }
        return null;
    }

    private Object caseString2Value(String paramStrVal, Class<?> aClass) {
        if (aClass == Integer.class) {
            return Integer.valueOf(paramStrVal);
        } else if (aClass==int.class) {
            return Integer.valueOf(paramStrVal).intValue();
        } else if (aClass == String.class) {
            return paramStrVal;
        }
        return null;//其余很多类型非常复杂，这里就不模拟了
    }
}
