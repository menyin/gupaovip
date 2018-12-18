package com.gupaoedu.vip.spring.v1.framework.servlet;

import com.gupaoedu.vip.spring.v1.framework.annotation.Autowrire;
import com.gupaoedu.vip.spring.v1.framework.annotation.Controller;
import com.gupaoedu.vip.spring.v1.framework.annotation.Mapping;
import com.gupaoedu.vip.spring.v1.framework.annotation.Service;
import com.gupaoedu.vip.spring.v1.framework.bingding.Handler;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class DispatchServlet extends HttpServlet {
    private Properties contextConfig = new Properties();
    private List<String> className = new ArrayList<>();
    private Map<String, Object> maps = new HashMap<>();
    private List<Handler> handlerList = new ArrayList<>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //根据不同的Url将请求响应委派给它们处理
        StringBuffer requestURL = req.getRequestURL();
        String requestURI = req.getRequestURI();
        for (Handler handler : handlerList) {
            if(handler.getUrl().equals(requestURI)){
                try {
                    Object result = handler.getMethod().invoke(handler.getController());
                    PrintWriter writer = resp.getWriter();
                    writer.write(result.toString());
                    writer.flush();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //定位
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        //加载
        doScanner(contextConfig.getProperty("scanPackage"));
        //注册
        doRegistry();
        //自动依赖注入
        doAutowired();
        //注册路由处理
        doRouter();
        super.init(config);
    }

    private void doRouter() {
        try {
            if (maps.isEmpty()) {
                return;
            }
            Set<Map.Entry<String, Object>> entries = maps.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                if(entry.getValue().getClass().isAnnotationPresent(Controller.class)){
                    String baseUrl = "";
                    if (entry.getValue().getClass().isAnnotationPresent(Mapping.class)) {
                        baseUrl += entry.getValue().getClass().getAnnotation(Mapping.class).value();
                    }
                    Method[] methods = entry.getValue().getClass().getMethods();
                    for (Method method : methods) {
                        if (method.isAnnotationPresent(Mapping.class)) {
                            Mapping annotation = method.getAnnotation(Mapping.class);
                            String mappingUrl = annotation.value();
                            if(!mappingUrl.substring(0, 1).equals("/")){
                                mappingUrl = "/" + mappingUrl;
                            }
                            if(baseUrl.length()>0&&!baseUrl.substring(0, 1).equals("/")){
                                baseUrl = "/" + baseUrl;
                            }
                            handlerList.add(new Handler(entry.getValue(), method, baseUrl + mappingUrl));
                        }
                    }
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doLoadConfig(String contextConfigLocation) {
        InputStream is = null;
        try {
            is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation.replace("classpath:", ""));
            contextConfig.load(is);
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
//        contextConfig
    }

    private void doScanner(String packName) {
        String s = "";
        URL resource = this.getClass().getClassLoader().getResource("/" + packName.replaceAll("\\.", "/"));
        File rootDir = new File(resource.getFile());
        for (File file : rootDir.listFiles()) {
            if (file.isDirectory()) {
                doScanner(packName + "." + file.getName());
            } else {
                className.add((packName + "." + file.getName()).replace(".class", ""));
            }
        }

    }

    private void doRegistry() {
        try {
            for (String clazz : className) {
                Class<?> aClass = Class.forName(clazz);
                if (aClass.isAnnotationPresent(Controller.class)) {
                    maps.put(setFirstWordToLower(aClass.getSimpleName()), aClass.newInstance());

                } else if (aClass.isAnnotationPresent(Service.class)) {
                    Service service = aClass.getAnnotation(Service.class);
                    String beanName = setFirstWordToLower(aClass.getInterfaces()[0].getSimpleName());
                    String value = service.value();
                    if (!"".equals(value.trim())) {
                        beanName = value;
                    }
                    maps.put(beanName, aClass.newInstance());

                } else if (aClass.isAnnotationPresent(Autowrire.class)) {


                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doAutowired()  {
        try {
            if (maps.isEmpty()) {
                return;
            }
            Set<Map.Entry<String, Object>> entries = maps.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                Field[] declaredFields = entry.getValue().getClass().getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    if (declaredField.isAnnotationPresent(Autowrire.class)) {
                        Autowrire annotation = declaredField.getAnnotation(Autowrire.class);
                        String beanName = declaredField.getName();
                        String value = annotation.value();
                        if (!"".equals(value)) {
                            beanName = value;
                        }
                        Object bean = maps.get(beanName);
                        if (bean != null) {
                            declaredField.setAccessible(true);
                            declaredField.set(entry.getValue(), bean);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void destroy() {
        super.destroy();
    }

    private String setFirstWordToLower(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
