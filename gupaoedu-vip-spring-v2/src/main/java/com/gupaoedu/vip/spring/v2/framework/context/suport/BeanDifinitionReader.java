package com.gupaoedu.vip.spring.v2.framework.context.suport;

import com.gupaoedu.vip.spring.v2.framework.annotation.Controller;
import com.gupaoedu.vip.spring.v2.framework.annotation.Service;
import com.gupaoedu.vip.spring.v2.framework.beans.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BeanDifinitionReader {
    private Properties config = new Properties();
    private List<String> registyBeanClasses = new ArrayList<>();
    public BeanDifinitionReader(String... configLocations) {
        InputStream is = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream(configLocations[0].replace("classpath:", ""));
            config.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        doScanner(config.getProperty("scanPackage"));
    }

    public List<String> loadBeanDefinitions(){
        return registyBeanClasses;
    }

    private void doScanner(String packName) {
        String s = "";
        URL resource = this.getClass().getClassLoader().getResource("/" + packName.replaceAll("\\.", "/"));
        File rootDir = new File(resource.getFile());
        for (File file : rootDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(packName + "." + file.getName());
            } else {
                //这里应该要加上注解过滤，有注解的才加载进去
                String fullName = (packName + "." + file.getName()).replace(".class", "");
                try {
                    Class<?> aClass = Class.forName(fullName);
                    if (aClass.isAnnotationPresent(Controller.class)||aClass.isAnnotationPresent(Service.class)) {
                        registyBeanClasses.add(fullName);
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 根据类全名生成beanDefinition
     * @param className 类全名
     * @return
     */
    public BeanDefinition registerBean(String className) {
        if (this.registyBeanClasses.contains(className)) {
            BeanDefinition beanDefinition = new BeanDefinition();
            beanDefinition.setClassName(className);
            beanDefinition.setFactoryBeanName(setFirstWordToLower(className.substring(className.lastIndexOf(".")+1)));
            return beanDefinition;
        }
        return null;
    }
    private String setFirstWordToLower(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    public Properties getConfig() {
        return config;
    }
}
