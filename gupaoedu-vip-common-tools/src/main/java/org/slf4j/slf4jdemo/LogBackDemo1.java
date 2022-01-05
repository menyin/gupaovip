package org.slf4j.slf4jdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 学习详见blog《ava日志框架：logback详解》
 */
public class LogBackDemo1 {
    private static Logger logger = LoggerFactory.getLogger(LogBackDemo1.class);

    public static void main(String[] args) throws IOException {
        //基本使用
        //1.pom配置slf4j-api、logback-classic
        //2.配置logbck.xml
        logger.info("社会主义好1");
        logger.debug("社会主义好2");
        logger.warn("社会主义好3");
        logger.error("社会主义好4");

        //测试logback.xml更新后自动加载
        new Thread(() ->{
            try {
                Thread.sleep(15000);
                logger.info("资本主义是个坏宝宝1");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.in.read();
    }
}
