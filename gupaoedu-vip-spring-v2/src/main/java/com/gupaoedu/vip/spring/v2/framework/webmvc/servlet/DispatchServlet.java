package com.gupaoedu.vip.spring.v2.framework.webmvc.servlet;

import com.gupaoedu.vip.spring.v2.framework.annotation.Controller;
import com.gupaoedu.vip.spring.v2.framework.annotation.Mapping;
import com.gupaoedu.vip.spring.v2.framework.annotation.RequestParam;
import com.gupaoedu.vip.spring.v2.framework.aop.AopProxyUtils;
import com.gupaoedu.vip.spring.v2.framework.context.ApplicationContext;
import com.gupaoedu.vip.spring.v2.framework.webmvc.HandlerAdaptor;
import com.gupaoedu.vip.spring.v2.framework.webmvc.HandlerMapping;
import com.gupaoedu.vip.spring.v2.framework.webmvc.ModelAndView;
import com.gupaoedu.vip.spring.v2.framework.webmvc.ViewResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

public class DispatchServlet extends HttpServlet {
    private List<HandlerMapping> handlerMappings = new ArrayList<>();
    private Map<HandlerMapping, HandlerAdaptor> handlerAdaptors = new HashMap<>();
    private List<ViewResolver> viewResolvers =new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            doDispatcher(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HandlerMapping handlerMapping = getHandlerMapping(req);
        if (handlerMapping != null) {
            HandlerAdaptor handlerAdaptor = getHandlerAdaptor(handlerMapping);
            ModelAndView modelAndView = handlerAdaptor.handle(req, resp, handlerMapping);
            processDispatherResult(resp, modelAndView);
        } else {
            resp.getWriter().write("<font size='25' color='red'>404 Not Found</font><br/><font color='green'><i>Copyright@GupaoEDU</i></font>");
            return;
        }
    }

    private void processDispatherResult(HttpServletResponse resp, ModelAndView modelAndView) throws Exception {
        if (modelAndView == null) {
            return;
        }
        for (ViewResolver viewResolver : this.viewResolvers) {
            if (modelAndView.getViewName().equals(viewResolver.getName())) {
                String result = viewResolver.resolver(modelAndView);
                resp.getWriter().write(result);
                break;
            }

        }

    }

    private HandlerAdaptor getHandlerAdaptor(HandlerMapping handlerMapping) {
        if (this.handlerAdaptors.isEmpty()) {
            return null;
        }
        return this.handlerAdaptors.get(handlerMapping);
    }

    private HandlerMapping getHandlerMapping(HttpServletRequest req) {
        String requestURI = req.getRequestURI().replaceAll("/+", "/");
        for (HandlerMapping handlerMapping : this.handlerMappings) {
            Pattern pattern = handlerMapping.getPattern();
            if (pattern.matcher(requestURI).matches()) {
                return handlerMapping;
            }
        }
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //SpringIOC容器初始化
        String contextConfigLocation = config.getInitParameter("contextConfigLocation");
        ApplicationContext applicationContext = new ApplicationContext(contextConfigLocation);
        initStragies(applicationContext);
    }


    @Override
    public void destroy() {
        super.destroy();
    }

    /**
     * 初始化SpringMvc流程中需要的一些策略
     * 这些策略全部通过SpringMvcIOC容器获得
     *
     * @param applicationContext
     */
    private void initStragies(ApplicationContext applicationContext) {
        initMutipartResolver(applicationContext);
        initLocalResolver(applicationContext);
        initThemeResolver(applicationContext);
        initHandlerMappings(applicationContext);//处理url-method的映射存储，自己实现
        initHandlerAdaptors(applicationContext);//处理请求到method的参数的适配，让请求找对应的参数方法，自己实现
        initHandlerExceptionResolvers(applicationContext);
        initRequestViewNameTranslators(applicationContext);
        initViewResolvers(applicationContext);//数据的视图解析器，自己实现
        initFlashMapManager(applicationContext);
    }

    private void initFlashMapManager(ApplicationContext applicationContext) {


    }

    private void initViewResolvers(ApplicationContext applicationContext) {
        Properties config = applicationContext.getConfig();
        String templateRoot = config.getProperty("templateRoot");
        String views = this.getClass().getClassLoader().getResource("").getPath().replace("WEB-INF/classes/", "views");
        String templateRootPath = views;
        File templateRootDir = new File(templateRootPath);
        for (File template : templateRootDir.listFiles()) {
            if (template.isFile()) {
                this.viewResolvers.add(new ViewResolver(template.getName(), template));
            }
        }


    }

    private void initRequestViewNameTranslators(ApplicationContext applicationContext) {

    }

    private void initHandlerExceptionResolvers(ApplicationContext applicationContext) {

    }

    private void initHandlerAdaptors(ApplicationContext applicationContext) {
        for (HandlerMapping handlerMapping : this.handlerMappings) {
            Map<String, Integer> paramMapping = new HashMap<>();
            Method method = handlerMapping.getMethod();
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            //处理用@RquestParam注解的参数
            for (int i = 0; i < parameterAnnotations.length; i++) {
                for (Annotation annotation : parameterAnnotations[i]) {
                    if (annotation instanceof RequestParam) {
                        RequestParam requestParam = (RequestParam) annotation;
                        String value = requestParam.value();
                        if (!value.equals("")) {
                            paramMapping.put(value, i);
                        }
                    }
                }
            }
            //处理未注解的参数,这里省略了
            //处理reques和response参数
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if (parameterTypes[i] == HttpServletRequest.class || parameterTypes[i] == HttpServletResponse.class) {
                    paramMapping.put(parameterTypes[i].getName(), i);
                }
            }
            handlerAdaptors.put(handlerMapping, new HandlerAdaptor(paramMapping));
        }
    }

    private void initHandlerMappings(ApplicationContext applicationContext) {
        String[] names = applicationContext.getBeanDefinitionNames();
        for (String name : names) {/*ApplicationContextAware*/
            Object proxy = applicationContext.getBean(name);
            Object controller = AopProxyUtils.getTargetObject(proxy);
            Class<?> controllerClass = controller.getClass();

            if (controllerClass.isAnnotationPresent(Controller.class)) {
                String baseUrl = "";
                if (controllerClass.isAnnotationPresent(Mapping.class)) {
                    Mapping annotation = controllerClass.getAnnotation(Mapping.class);
                    String value = annotation.value();
                    baseUrl += value;

                }
                Method[] methods = controllerClass.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(Mapping.class)) {
                        Mapping methodAnnotation = method.getAnnotation(Mapping.class);
                        String methodVlue = methodAnnotation.value();
                        String url = "/" + baseUrl + "/" + methodVlue;
                        //第一个replaceAll是将多个/替换一个
                        //第二个replaceAll是将形如/user/add*替换成正则表达式字符串/user/add.*
                        Pattern pattern = Pattern.compile(url.replaceAll("/+", "/").replaceAll("\\*", ".*"));
                        handlerMappings.add(new HandlerMapping(controller, method, pattern));
                    }
                }
            }


        }
    }

    private void initThemeResolver(ApplicationContext applicationContext) {
    }

    private void initLocalResolver(ApplicationContext applicationContext) {
    }

    private void initMutipartResolver(ApplicationContext applicationContext) {
    }
}
