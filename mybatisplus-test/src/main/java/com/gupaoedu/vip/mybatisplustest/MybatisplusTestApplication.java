package com.gupaoedu.vip.mybatisplustest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gupaoedu.vip.mybatisplustest.mappers")
public class MybatisplusTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisplusTestApplication.class, args);
    }

}
