package com.example.springbootdemo.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2023年03月08日 9:17
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                //表示是否允许发送Cookie。默认情况下Cookie不包括在CORS请求中。当设为true时表示服务器明确许可，Cookie可以包含在请求中一起发送给服务器。
//                .allowCredentials(true)
                .maxAge(3600 * 24);
    }
}
