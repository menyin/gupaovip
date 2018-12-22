package com.gupaoedu.vip.spring.v2.framework.context;

import com.gupaoedu.vip.spring.v2.framework.annotation.Controller;
import com.gupaoedu.vip.spring.v2.framework.annotation.Service;
import com.gupaoedu.vip.spring.v2.framework.aop.AopConfig;
import com.gupaoedu.vip.spring.v2.framework.beans.BeanDefinition;
import com.gupaoedu.vip.spring.v2.framework.beans.BeanWrapper;
import com.gupaoedu.vip.spring.v2.framework.context.suport.BeanDifinitionReader;
import com.gupaoedu.vip.spring.v2.framework.core.BeanFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext extends DefaultListableBeanFactory implements BeanFactory{
    private String[] configLocations;
    private BeanDifinitionReader reader;
    private Map<String, Object> beanCacheMap = new HashMap<>();
    private Map<String, BeanWrapper> beanWrapperMap = new ConcurrentHashMap<>();

    public ApplicationContext(String ... configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    private void refresh() {
        //定位
        this.reader = new BeanDifinitionReader(configLocations);

        //加载
        List<String> beanDefinitions=reader.loadBeanDefinitions();

        //注册
        doRegistry(beanDefinitions);

        //自动注入,其中会调用getBean方法
        doAutowrited();
    }

    private void doAutowrited() {
        Set<Map.Entry<String, BeanDefinition>> entries = beanDefinitionMap.entrySet();
        for (Map.Entry<String, BeanDefinition> entry : entries) {
            BeanDefinition beanDefinition = entry.getValue();
            if(!beanDefinition.isInitLazy()){
                getBean(beanDefinition.getFactoryBeanName());
            }

        }

    }


    private void doRegistry(List<String>  beanDefinitions) {
        try {
            for (String className : beanDefinitions) {
                Class<?> aClass = Class.forName(className);
                BeanDefinition beanDefinition=reader.registerBean(className);
                if(beanDefinition!=null){
                    beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
                    Class<?>[] interfaces = aClass.getInterfaces();
                    for (Class<?> anInterface : interfaces) {
                        beanDefinitionMap.put(anInterface.getName(), beanDefinition);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        if(beanDefinition!=null){
            Object bean = beanCacheMap.get(beanName);
            if (bean == null) {
//                String className = beanDefinition.getClassName();
                Object instance = intantionBean(beanDefinition);
                if (instance == null) {
                    return null;
                }else{
                    BeanWrapper beanWrapper = new BeanWrapper(instance);
                    beanWrapper.setAopConfig(new AopConfig());
                    beanWrapper.setPostProcessor(new Object());//事件监听

                    return beanWrapperMap.put(beanName, beanWrapper).getWrappedInstance();
                }

            }
            return bean;
        }
        return null;
    }

    private Object intantionBean(BeanDefinition beanDefinition) {
        String className = beanDefinition.getClassName();
        if (beanCacheMap.containsKey(beanDefinition.getFactoryBeanName())) {
            return beanCacheMap.get(beanDefinition.getFactoryBeanName());
        }
        try {
            Class<?> aClass = Class.forName(className);
            Object instance = aClass.newInstance();
            beanCacheMap.put(beanDefinition.getFactoryBeanName(), instance);
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
