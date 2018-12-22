package com.gupaoedu.vip.spring.v2.framework.context;

import com.gupaoedu.vip.spring.v2.framework.beans.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public class DefaultListableBeanFactory extends AbstractApplictionContext {
    protected Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();
    protected void onRefresh(){

    }

    @Override
    protected void refreshBeanfactory() {

    }
}
