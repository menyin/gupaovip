package com.gupaoedu.vip.redis.spring.data.redis;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * spring-data-redis 分布式锁实现
 * https://www.jianshu.com/p/19e851a3edba
 */
public class Demo5 {
    static int NUM = 0;// 共享变量
    private RedisLockUtil redisLockUtil=new RedisLockUtil();

    /**
     * 测试场景：100 个线程同时修改一个静态常量值 NUM（初始值为0），每个线程加1，如果不加锁会看到打印结果不都是100。
     *          每个线死循环确保一定获取锁且执行＋１操作。这样的锁竞争是比较大了。
     * 预期结果：每次执行结果都是100
     */
    @Test
    public void test1(){
        String key = "prefix_key123456"; // redis 锁的 key 值
        String expireTime = "5000";// 锁的超时时间(毫秒)，评估任务时间，建议任务的时间不要太长
        int retryTimes = 3;// 获取锁的重试次数


        int threasCount = 100;// 线程任务个数

        List<Thread> list = new ArrayList<Thread>();
        for (int i = 0; i < threasCount; i++) {
            list.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    // request id, 防止解了不该由自己解的锁。
                    String requestId = UUID.randomUUID().toString();
                    while (true) { //这里循环操作，以确保该线程一定获得锁并执行线程任务

                        if (redisLockUtil.lock(key, requestId, expireTime, retryTimes)) {
                            try {
                                // 调用业务逻辑,这里的业务逻辑就是访问并变更共享资源NUM
                                NUM++;
                            } finally {
                                redisLockUtil.unLock(key, requestId);
                            }
                            break;
                        }
                    }

                }
            }));
        }

        //启动所有任务线程
        for (Thread t : list) {
            t.start();
        }

        //轮询状态，等待所有子线程完成
        while (true) {
            int aliveThreadCount = 0;
            for (Thread t : list) {
                if (t.isAlive()) {
                    ++aliveThreadCount;
                }
            }

            if (aliveThreadCount == 0) {
//                log.debug("All Threads are completed!");
                System.out.println("All Threads are completed!");
                break;
            } else {
//                log.debug("Threads have not yet completed， sleep 5s!");
                System.out.println("Threads have not yet completed， sleep 5s! 共"+aliveThreadCount);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //打印最终结果值： NUM
        System.out.println("Completed! The final value of NUM is : "+NUM);

    }

    /**
     * 测试场景：1000 个线程同时修改一个静态常量值 NUM（初始值为0），每个线程加1，不加锁会看到打印结果不都是100。
     *          每个线死循环确保一定获取锁且执行＋１操作。这样的锁竞争是比较大了。
     * 预期结果：每次执行结果不一定都是1000
     *          （为了增加并发几率，设置了1000个线程）
     */
    @Test
    public void test2(){
        CountDownLatch countDownLatch = new CountDownLatch(1000);
        int threasCount = 1000;// 线程任务个数

        List<Thread> list = new ArrayList<Thread>();
        for (int i = 0; i < threasCount; i++) {
            list.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();//模拟并发
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 调用业务逻辑,这里的业务逻辑就是访问并变更共享资源NUM
                    NUM=NUM+1;

                }
            }));
            countDownLatch.countDown();
        }

        //启动所有任务线程
        for (Thread t : list) {
            t.start();
        }

        //轮询状态，等待所有子线程完成
        while (true) {
            int aliveThreadCount = 0;
            for (Thread t : list) {
                if (t.isAlive()) {
                    ++aliveThreadCount;
                }
            }

            if (aliveThreadCount == 0) {
//                log.debug("All Threads are completed!");
                System.out.println("All Threads are completed!");
                break;
            } else {
//                log.debug("Threads have not yet completed， sleep 5s!");
                System.out.println("Threads have not yet completed， sleep 5s! 共"+aliveThreadCount);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //打印最终结果值： NUM
        System.out.println("Completed! The final value of NUM is : "+NUM);

    }




}
