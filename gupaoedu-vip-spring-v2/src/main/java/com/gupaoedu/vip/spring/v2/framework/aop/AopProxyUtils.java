package com.gupaoedu.vip.spring.v2.framework.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class AopProxyUtils {
    public static Object getTargetObject(Object proxy) {
        if (!isAopProxy(proxy)) {
            return proxy;
        }else{
            try {
                Object proxyTargetObject = getProxyTargetObject(proxy);
                return proxyTargetObject;
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    public static Boolean isAopProxy(Object proxy) {
        return Proxy.isProxyClass(proxy.getClass());
    }
    public  static Object getProxyTargetObject(Object proxy) throws NoSuchFieldException, IllegalAccessException {
        Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
        h.setAccessible(true);
        Object hVal = h.get(proxy);
        AopProxy aopProxy = (AopProxy) hVal;
        Field target = aopProxy.getClass().getDeclaredField("target");
        target.setAccessible(true);
        Object targetVal = target.get(aopProxy);
        return targetVal;
    }
}
