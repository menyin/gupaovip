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
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;
import java.util.Set;

/**
 * spring-data-redis execute 相关操作
 * 参考blogs《redisTemplate 操作》 csdn《RedisTemplate使用PipeLine的总结》
 *  总结：1.execute分RedisCallback、SessionCallback两种方式
 *       2.上述2者只是操作的方式和对象不同，但是都可以实现普通、管道、事务方面的需求。
 *       3.管道模式相当是在一次redis的请求-响应中批量处理多个命令，有点类似lua脚本。当然这些也都是在一次Connection中完成
 *       4.事务的实现有两种：使用executePipelined(RedisCallback rc) 详见https://yq.aliyun.com/articles/648282
 *                          使用execute(SessionCallback sc) 详见https://blog.csdn.net/qq_39455116/article/details/90749257
 *       5.execute的多有操作都是在一次Connection中完成，这也就是它和redisTemplate.opsForValue()等操作方式的区别。
 *       6.单机下支持事务、管道模式，RedisCLuster集群是不支持
 *
 */
public class Demo2 {
    private RedisTemplate<Object,Object> redisTemplate;
    @Before
    public void before(){
        ClassPathXmlApplicationContext ap = new ClassPathXmlApplicationContext("spring-redis-cluster.xml");
        redisTemplate = (RedisTemplate) ap.getBean("redisTemplate");
    }


    /**
     * 使用RedisCallback接口进行execute
     */
    @Test
    public void test1() {
        //普通excute执行（通过pipeline参数可以设置为管道模式）
        /*String execute = (String)redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                byte[] bytes = redisConnection.get("aa".getBytes());
                return new String(bytes);
            }

        }, false, false);*/
        //exposeConnection参数为true则redisConnection是未经过代理包装的原连接对象，断点调试下即可发现它们的区别
        //pipeline为true则可以进行管道操作（一次请求响应），类似于多个命令批量提交给redis服务，redis服务再批量响应，但是由于集群机器是分开等原因，在RedisCluster模式下是不支持的。
//        System.out.println(execute);

        //管道模式的excute执行
       /* List list = redisTemplate.executePipelined(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                Set<byte[]> keys = redisConnection.keys("excuteKey*".getBytes());
                keys.forEach(key->{
                    byte[] bytes = redisConnection.get(key);
                    if (bytes != null) {
                        redisConnection.rPop(bytes);
                    }
                });
                return null;
            }
        }, new StringRedisSerializer());

        for (Object o : list) {
            System.out.println(o);
        }*/


    }

    /**
     * 使用SessionCallback接口进行execute
     * 1.SessionCallback和RedisCallback均可以提供事务支持，只是使用方式不一样。详见下述test3()
     * 2.SessionCallback使用redisOperations进行操作，redisOperations是高级抽象，比较方便
     */
    @Test
    public void test2() {
        Object aa = redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations redisOperations) throws DataAccessException {
                Object aa = redisOperations.opsForValue().get("aa");
                return aa;
            }
        });
        System.out.println(aa);
    }

    /**
     * 事务的实现  ---> 详见 Demo3#test1
     */
    @Test
    public void test3(){

    }
}
