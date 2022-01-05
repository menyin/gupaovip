package com.gupaoedu.vip.spring.v2.framework.context;

import com.gupaoedu.vip.spring.v2.framework.annotation.Autowrire;
import com.gupaoedu.vip.spring.v2.framework.annotation.Controller;
import com.gupaoedu.vip.spring.v2.framework.aop.AopConfig;
import com.gupaoedu.vip.spring.v2.framework.beans.BeanDefinition;
import com.gupaoedu.vip.spring.v2.framework.beans.BeanWrapper;
import com.gupaoedu.vip.spring.v2.framework.context.suport.BeanDifinitionReader;
import com.gupaoedu.vip.spring.v2.framework.core.BeanFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationContext extends DefaultListableBeanFactory implements BeanFactory {
    private String[] configLocations;
    private BeanDifinitionReader reader;
    private Map<String, Object> beanCacheMap = new HashMap<>();
    private Map<String, BeanWrapper> beanWrapperMap = new ConcurrentHashMap<>();

    public ApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    private void refresh() {
        //定位
        this.reader = new BeanDifinitionReader(configLocations);

        //加载
        List<String> beanDefinitions = reader.loadBeanDefinitions();

        //注册
        doRegistry(beanDefinitions);

        //自动注入,其中会调用getBean方法
        doAutowrited();

    }

    private void doAutowrited() {
        try {
            //通过调用getBean()生成基础bean
            Set<Map.Entry<String, BeanDefinition>> entries = beanDefinitionMap.entrySet();
            for (Map.Entry<String, BeanDefinition> entry : entries) {
                BeanDefinition beanDefinition = entry.getValue();
                if (!beanDefinition.isInitLazy()) {
                    getBean(entry.getKey());
                }
            }
            //将上述基础bean的属性注入
            for (Map.Entry<String, BeanWrapper> stringBeanWrapperEntry : beanWrapperMap.entrySet()) {
                Object originInstance = stringBeanWrapperEntry.getValue().getOriginInstance();//Controller是
                if (originInstance.getClass().isAnnotationPresent(Controller.class)) {//这里只做Controller的Service注入，其它的同理可得
                    Field[] declaredFields = originInstance.getClass().getDeclaredFields();
                    for (Field declaredField : declaredFields) {
                        declaredField.setAccessible(true);
                        if (declaredField.isAnnotationPresent(Autowrire.class)) {
                            Autowrire annotation = declaredField.getAnnotation(Autowrire.class);
                            String beanName = annotation.value();
                            if (beanName.trim().equals("")) {
                                beanName = declaredField.getType().getName();
                            }
                            //getBean(beanName);
                            if (beanWrapperMap.containsKey(beanName)) {
                                Object fileValue = beanWrapperMap.get(beanName).getWrappedInstance();
                                declaredField.set(originInstance, fileValue);
                            } else {
                                throw new Exception("自动注入时未找到响应的bean实例");
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void doRegistry(List<String> beanDefinitions) {
        try {
            for (String className : beanDefinitions) {
                Class<?> aClass = Class.forName(className);
                BeanDefinition beanDefinition = reader.registerBean(className);
                if (beanDefinition != null) {
//                    beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);//和tom的思路稍有区别，我这里以接口名问bebeanDefinitionMap的key
                    Class<?>[] interfaces = aClass.getInterfaces();
                    for (Class<?> anInterface : interfaces) { //非Controller用接口名作为key，实际上应该像tom那样，不区分Controller，这个区分工作不属于IOC容器该干的事情
                        beanDefinitionMap.put(anInterface.getName(), beanDefinition);
                    }
                    //Controller直接用简单类名作为key
                    if (aClass.isAnnotationPresent(Controller.class)) {
                        beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
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
        if (beanDefinition != null) {
            Object bean = beanCacheMap.get(beanName);
            if (bean == null) {
//                String className = beanDefinition.getClassName();
                Object instance = intantionBean(beanDefinition);
                if (instance == null) {
                    return null;
                } else {
                    BeanWrapper beanWrapper = new BeanWrapper(instance);
                    reader.getConfig().getProperty("");
                    beanWrapper.setAopConfig(instantionAopConfig(beanDefinition));
                    beanWrapper.setPostProcessor(new Object());//事件监听
                    beanWrapperMap.put(beanName, beanWrapper);
                    return beanWrapper.getWrappedInstance();
                }

            }
            return bean;
        }
        return null;
    }

    private AopConfig instantionAopConfig(BeanDefinition definition){
        try {
            AopConfig aopConfig = new AopConfig();
            String pointCut = reader.getConfig().getProperty("pointCut");
            Pattern pattern = Pattern.compile(pointCut);
            String aspectBefore = reader.getConfig().getProperty("aspectBefore");
            String aspectAfter = reader.getConfig().getProperty("aspectAfter");
            String[] splitBefore = aspectBefore.split("\\s");
            String[] splitAfter = aspectAfter.split("\\s");
            Class<?> aspectClass = Class.forName(splitBefore[0]);
            Method[] aspectMethodss = new Method[2];
            aspectMethodss[0] = aspectClass.getMethod(splitBefore[1]);
            aspectMethodss[1] = aspectClass.getMethod(splitAfter[1]);

            String className = definition.getClassName();
            Class<?> aClass = Class.forName(className);
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {
                Matcher matcher = pattern.matcher(method.toString());
                if (matcher.matches()) {
                    aopConfig.put(method, aspectClass.newInstance(), aspectMethodss);
                }
            }
            return aopConfig;

        } catch (Exception e) {
            e.printStackTrace();
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

    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanWrapperMap.size()]);
    }

    public Properties getConfig() {
        return this.reader.getConfig();
    }
}
