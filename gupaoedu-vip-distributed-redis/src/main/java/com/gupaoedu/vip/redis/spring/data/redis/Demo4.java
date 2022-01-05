package com.gupaoedu.vip.redis.spring.data.redis;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * spring-data-redis lua脚本
 * https://www.jianshu.com/p/19e851a3edba
 * csdn《使用redistemplate调用lua脚本的简单应用场景》
 */
public class Demo4 {
    private RedisTemplate redisTemplate;
    private DefaultRedisScript<Long> script;
    private AtomicInteger count = new AtomicInteger(0);

    @Before
    public void before() {
        ClassPathXmlApplicationContext ap = new ClassPathXmlApplicationContext("spring-redis-single.xml");
        redisTemplate = (RedisTemplate) ap.getBean("redisTemplate");

        //创建lua脚本对象
        script = new DefaultRedisScript<Long>();
        script.setResultType(Long.class);
        script.setScriptSource(new ResourceScriptSource(new ClassPathResource("testlua.lua")));
    }

    /**
     * 基本应用
     * 同一ip访问,10s内并发不超过20
     * 10s后有重新计数 ???放到线程里去跑就不行！！是因为代码跑太快sout还没输出程序就结束了，所以后面加个Thread.sleep(10000)
     */
    @Test
    public void test1() throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                Object exec = exec("192.168.1.123");
                System.out.println("192.168.1.123 - " + exec);
            }).start();
        }
        Thread.sleep(10000);//只有加这个，sout才够时间跑完
    }


    public Object exec(String ip) {
        List<String> keys = new ArrayList<>();
        keys.add("ipaccess_" + ip);
        System.out.println(2222);
        Object execute = redisTemplate.execute(script, keys, "10", "20");//由于自定义的序列化器是会将10、20数字当成Object进行序列化，所以要写成"10", "20"
        System.out.println(3333);
        return execute;
    }

}
