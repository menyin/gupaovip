package org.slf4j.slf4jdemo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 学习详见blog《Java日志框架：slf4j作用及其实现原理》
 *        简书《关于slf4j log4j log4j2的jar包配合使用的那些事》
 */
public class Demo1 {
    private static Logger logger = LoggerFactory.getLogger(Demo1.class);

    public static void main(String[] args) {
        logger.info("社会主义好1");
        logger.debug("社会主义好2");
        logger.warn("社会主义好3");
        logger.error("社会主义好4");
    }
}
