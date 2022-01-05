package com.gupaoedu.vip.spring.v2.framework.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class AopProxy implements InvocationHandler {

    private Object target;
    private AopConfig aopConfig;

    public Object getProxy(Object instance) {
       this.target = instance;
       return Proxy.newProxyInstance(instance.getClass().getClassLoader(), instance.getClass().getInterfaces(), this);
    }
    public void setConfig(AopConfig aopConfig){
        this.aopConfig = aopConfig;

    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //此处应注意，在aopConfig存储对象方法与切面的关系时是存储原生对象的方法，而此处的method是原生对象的接口的方法。
        Method originMehtod = target.getClass().getMethod(method.getName(), method.getParameterTypes());

        //根据aopConfig配置的信息进行拦截
        if (aopConfig != null && aopConfig.contains(originMehtod)) {
            AopConfig.Aspect aspect = aopConfig.get(originMehtod);
            Method[] methods = aspect.getMethods();
            methods[0].invoke(aspect.getAspect());
            Object invoke = method.invoke(target, args);
            methods[1].invoke(aspect.getAspect());
            return invoke;
        } else {
            Object invoke = method.invoke(target, args);
            return invoke;
        }
    }

}
