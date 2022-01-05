package gupaoedu.vip.spring.customerinterface;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class MyApplicationContextInitializer implements ApplicationContextInitializer {
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("公共拦截：进入ApplicationContextInitializer接口initialize()方法 ....");
        System.out.println(configurableApplicationContext);
    }
}
