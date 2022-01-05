package com.gupaoedu.vip.spring.v2.framework.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AopConfig {
    private Map<Method, Aspect> points = new HashMap<>();
    public void put(Method targetMethod,Object aspectObject,Method[] aspectPoints){
        this.points.put(targetMethod, new Aspect(aspectObject, aspectPoints));
    }
    public Aspect get(Method targetMethod){
        return this.points.get(targetMethod);
    }
    public boolean contains(Method targetMethod){
        return this.points.containsKey(targetMethod);
    }

    public class Aspect{
        private Object aspect;
        private Method[] methods;

        public Aspect(Object aspect, Method[] methods) {
            this.aspect = aspect;
            this.methods = methods;
        }

        public Object getAspect() {
            return aspect;
        }

        public void setAspect(Object aspect) {
            this.aspect = aspect;
        }

        public Method[] getMethods() {
            return methods;
        }

        public void setMethods(Method[] methods) {
            this.methods = methods;
        }
    }
}
