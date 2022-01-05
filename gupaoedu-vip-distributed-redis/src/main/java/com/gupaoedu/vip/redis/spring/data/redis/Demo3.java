package com.gupaoedu.vip.redis.spring.data.redis;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * spring-data-redis 事务性操作
 * 参考csdn《java 使用RedisTemplate实现Redis事务》，blogs《SpringDataRedis事务 专题》，csdn《redis一些关于SessionCallback和事务要注意的地方》
 * redis的事务和DB的事务是有本质的区别的，redis事务是没有回滚的。也就是redis事务更像是批量命令。
 */
public class Demo3 {
    private RedisTemplate<Object,Object> redisTemplate;

    @Before
    public void before(){
        ClassPathXmlApplicationContext ap = new ClassPathXmlApplicationContext("spring-redis-single.xml");
        redisTemplate = (RedisTemplate) ap.getBean("redisTemplate");
    }
    /**
     * 常规事务的实现
     * 当没有watch监控时，当有代码异常则DISCARD
     */
    @Test
    public void test1() throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Callable<Object>> tasks = new ArrayList<>();
//        executorService.invokeAll()
        for (int i = 0; i < 100; i++) {
            final int index = i;
            tasks.add(() -> {
                return redisTemplate.execute(new SessionCallback() {
                    @Override
                    public Object execute(RedisOperations redisOperations) throws DataAccessException {
                        //设置监控key,在exec执行前如果这个key对应的值，发生了变化，事务bu执行
                        //通常监控的key可以是ID，也可以是一个对象
                        redisOperations.multi();//事务开始标记
                        try {//捕捉异常
                            Integer result1 = redisOperations.opsForValue().append("b", "B-" + index + "+");//事务相关的业务代码1
                            if (index == 3 || index == 6 || index == 9) {//模拟异常
                                int p = 1 / 0;
                            }
                            Integer reuslt2 = redisOperations.opsForValue().append("b", " b-" + index + "\r\n");//事务相关的业务代码2
                            System.out.println("result1:" + result1 + "  reuslt2:" + reuslt2);//实际上无论怎么样result1、result2是没有值的，因为实际上只有等到exec()执行后才会将所有指令一次性执行。
                            //特别注意exec();放discard();之前，因为discard后事务就没了，再执行exec就会报错。
                            List exec = redisOperations.exec();//事务结束标记，并且在没“问题”情况下提交。返回结果不为[],则说明有正常执行，即事务有提交了。
                            System.out.println("bb" + index + "  结果:" + exec);//exec是包含了multi()和exec()之间执行的所有redis指令执行的结果，这里就是上述两条代码执行的结果
                            return exec;
                        } catch (Exception e) {
                            redisOperations.discard();//异常的线程则不会提交事务
                            return null;
                        }

                    }
                });
            });

        }
        List<Future<Object>> futures = executorService.invokeAll(tasks);

        for (Future<Object> future : futures) {
            System.out.println(future.get());
        }

        System.out.println(redisTemplate.opsForValue().get("b"));//最后查看结果，发现只有其中一个线程执行了业务代码，说明只有一个线程正确提交了事务

    }

    /**
     * 事务的实现
     * 有watch相当于有一个抢锁的动作，保证唯一一个用户线程执行
     */
    @Test
    public void test2() throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Callable<Object>> tasks = new ArrayList<>();
//        executorService.invokeAll()
        for (int i = 0; i < 5; i++) {
            final int index = i;
            tasks.add(() -> {
                return redisTemplate.execute(new SessionCallback() {
                    @Override
                    public Object execute(RedisOperations redisOperations) throws DataAccessException {
                        //设置监控key,在exec执行前如果这个key对应的值，发生了变化，事务bu执行
                        //通常监控的key可以是ID，也可以是一个对象
                        redisOperations.watch("aa");//监控一个redis的key对象，这个监控必须在SessionCallback里面
                        redisOperations.multi();//事务开始标记
                        Long result1 = redisOperations.opsForValue().increment("aa", 1);//改变监控值，当第一个进入该代码的线程先改变了监控值，其它线程再进入就只能被隔离在外了。
                        Integer reuslt2 = redisOperations.opsForValue().append("b", "b-" + index);//事务相关的业务代码
                        List exec = redisOperations.exec();//事务结束标记，并且在没“问题”情况下提交。返回结果不为[],则说明有正常执行，即事务有提交了。
                        System.out.println("bb" + index + "  结果:" + exec);//exec是包含了multi()和exec()之间执行的所有redis指令执行的结果，这里就是上述两条代码执行的结果
                        System.out.println("result1:" + result1 + "  reuslt2:" + reuslt2);//实际上无论怎么样result1、result2是没有值的，因为实际上只有等到exec()执行后才会将所有指令一次性执行。
                        return exec;
                    }
                });
            });

        }
        List<Future<Object>> futures = executorService.invokeAll(tasks);

        for (Future<Object> future : futures) {
            System.out.println(future.get());
        }

        System.out.println(redisTemplate.opsForValue().get("b"));//最后查看结果，发现只有其中一个线程执行了业务代码，说明只有一个线程正确提交了事务

    }



    //    @Transactional

    /**
     * Spring 提供的注解进行处理事务
     * 在调用RedisTempalte中的execute()方法的地方使用@Transactional
     * 还未进行具体实例的测试
     */
    @Test
    public void test3() {


    }


}
