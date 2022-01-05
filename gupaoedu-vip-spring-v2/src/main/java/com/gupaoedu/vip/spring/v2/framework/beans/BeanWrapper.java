package com.gupaoedu.vip.spring.v2.framework.beans;

import com.gupaoedu.vip.spring.v2.framework.aop.AopConfig;
import com.gupaoedu.vip.spring.v2.framework.aop.AopProxy;

public class BeanWrapper {
    private Object wrapperInstance;
    private Object originInstance;
    private AopProxy aopProxy = new AopProxy();
    private Object postProcessor;

    public Object getPostProcessor() {
        return postProcessor;
    }

    public void setPostProcessor(Object postProcessor) {
        this.postProcessor = postProcessor;
    }

    public BeanWrapper(Object instance) {
        this.wrapperInstance = aopProxy.getProxy(instance);
        this.originInstance = instance;
    }

    public Object getWrappedInstance() {
        return this.wrapperInstance;
    }

    public Object getOriginInstance() {
        return originInstance;
    }

    public void setOriginInstance(Object originInstance) {
        this.originInstance = originInstance;
    }

    public void setAopConfig(AopConfig aopConfig) {
        aopProxy.setConfig(aopConfig);
    }

}
