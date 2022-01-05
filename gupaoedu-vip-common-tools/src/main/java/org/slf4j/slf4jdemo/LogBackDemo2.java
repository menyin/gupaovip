package org.slf4j.slf4jdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * 学习详见blog《ava日志框架：logback详解》
 */
public class LogBackDemo2 {

    private static Logger logger = LoggerFactory.getLogger("cny.console");

    public static void main(String[] args) throws IOException {
        /*logger.info("社会主义好1");
        logger.debug("社会主义好2");
        logger.warn("社会主义好3");
        logger.error("社会主义好4");*/
/*
        HashMap<String, Object> map = new HashMap<>();
        map.put("jack", "杰克");
        BaseClass childClass = new ChildClass(map);
        HashMap<String, Object> mapDe= childClass.getBody();
        System.out.println(mapDe.get("jack"));
        System.in.read();
*/
        byte[] body = new BaseClass().getBody();
        System.in.read();

    }
}
