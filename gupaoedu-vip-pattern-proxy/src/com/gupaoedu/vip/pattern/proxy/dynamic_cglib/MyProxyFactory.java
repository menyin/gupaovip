package com.gupaoedu.vip.pattern.proxy.dynamic_cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyProxyFactory {

    /*public static Object getProxyInstance(Object target) {
        MethodInterceptor methodInterceptor = new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                long l = System.currentTimeMillis();
                System.out.println(l+"日志记录begin");//此处如果打断点，日志输出会出现“诡异现象” 是因为对象o的toString被执行了。
                Object result = method.invoke(target, args);
                System.out.println(l+"日志记录end");
                return result;
            }
        };
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(methodInterceptor);
        Object proxyObj = enhancer.create();
        return proxyObj;
    }*/

    /**
     * 用lambda改造上面的代码
     * @param target
     * @return
     */
    public static Object getProxyInstance(Object target) {
        MethodInterceptor methodInterceptor =( o, method ,  args,  methodProxy) ->
        {
            long l = System.currentTimeMillis();
            System.out.println(l+"日志记录begin");
            Object result = methodProxy.invoke(target, args);
            System.out.println(l+"日志记录end");
            return result;
        };
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(methodInterceptor);
        Object proxyObj = enhancer.create();
        return proxyObj;
    }
}
