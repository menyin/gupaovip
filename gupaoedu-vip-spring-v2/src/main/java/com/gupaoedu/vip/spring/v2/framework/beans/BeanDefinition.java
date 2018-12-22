package com.gupaoedu.vip.spring.v2.framework.beans;

public class BeanDefinition {
    private String className;
    private boolean initLazy=false;
    private String factoryBeanName;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isInitLazy() {
        return initLazy;
    }

    public void setInitLazy(boolean initLazy) {
        this.initLazy = initLazy;
    }

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }
}
