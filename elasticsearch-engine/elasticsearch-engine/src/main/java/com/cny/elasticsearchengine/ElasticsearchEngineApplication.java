package com.cny.elasticsearchengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class ElasticsearchEngineApplication {

    public static void main(String[] args) {
        System.out.println(new Date().getSeconds());
        SpringApplication.run(ElasticsearchEngineApplication.class, args);
    }

}
