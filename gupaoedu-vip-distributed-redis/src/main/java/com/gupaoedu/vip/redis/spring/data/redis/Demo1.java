package com.gupaoedu.vip.redis.spring.data.redis;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * spring-data-redis 1.7.0+后指出redis-cluster集群模式，xml配置和单机不同
 * 基本操作
 */
public class Demo1 implements Serializable {
    /**
     * 注意 StringRedisTemplate 继承 RedisTemplate<String, String>
     */
    private RedisTemplate<Object, Object> redisTemplate;


    @Before
    public void before() {
        ApplicationContext apc = new ClassPathXmlApplicationContext("spring-redis-cluster.xml");
        redisTemplate = (RedisTemplate) apc.getBean("redisTemplate");
    }


    /**
     * redisTemplate的基本操作
     */
    @Test
    public void test0() {
        /*----------String begin----------*/
        //String 类型key-value 数据操作
        redisTemplate.opsForValue().set("redisTemplate4string1", "1111");
        Object redisTemplate4string1 = redisTemplate.opsForValue().get("redisTemplate4string1");
        System.out.println(redisTemplate4string1);


        //String 类型key，Object类型value 数据操作
        /*redisTemplate.opsForValue().set("redisTemplate4string2",123);
        Object redisTemplate4string2 = redisTemplate.opsForValue().get("redisTemplate4string2");
        System.out.println(redisTemplate4string2);*/

        //Object类型key,String 类型value 数据操作
        /*redisTemplate.opsForValue().set(123,"123");
        Object redisTemplate4string3 = redisTemplate.opsForValue().get(123);
        System.out.println(redisTemplate4string3);*/

        //设置值并设置过期时间
        /*redisTemplate.opsForValue().set("redisTemplate4string1","456",30000,TimeUnit.MILLISECONDS);
        Long expire = redisTemplate.getExpire("redisTemplate4string1");
        System.out.println(redisTemplate.opsForValue().get("redisTemplate4string1"));
        System.out.println("过期时间为："+expire);*/

        /*----------String end----------*/


        /*----------Hash begin----------*/

        //hash 基本的赋值
        /*redisTemplate.opsForHash().put("redisTemplate4hash1","filed1","123");
        Object value1 = redisTemplate.opsForHash().get("redisTemplate4hash1", "filed1");
        System.out.println(value1);*/
        redisTemplate.opsForHash().put("redisTemplate4hash1", "filed1", "123");

        //hash 过期时间操作
        redisTemplate.expire("redisTemplate4hash1", 20, TimeUnit.SECONDS);
        System.out.println(redisTemplate.getExpire("redisTemplate4hash1"));

        /*----------Hash end----------*/

        /*----------其它......----------*/
        redisTemplate.opsForSet();
        redisTemplate.opsForList();

    }

    /**
     * 用execute中的Connection对象执行命令
     */
    @Test
    public void test1() {

        Integer execute = redisTemplate.execute(new RedisCallback<Integer>() {
            //这里返回值是个上面的RedisCallback<Integer> 中的泛型一直，
            public Integer doInRedis(RedisConnection connection) {
                int i = 0;
                for (; i < 100; i++) {
                    byte[] key = ("excuteKey:" + i).getBytes();
                    byte[] value = ("excuteValue:" + i).getBytes();
                    connection.set(key, value);
                }
                //这里返回值是个上面的RedisCallback<Integer> 中的泛型一直，
                return i;

            }
        });
        System.out.println(execute);
    }

    /**
     * 执行lua脚本
     */
    public static void test2() {
        //详见Demo4

    }


    /**
     * 测试如何通过序列化后的byte[]判断序列化前的对象是不是String
     */
    @Test
    public void test00() {
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        byte[] bytes = jdkSerializationRedisSerializer.serialize(new Demo1());
        String protocol = Integer.toHexString(bytes[0] & 0x000000ff) + Integer.toHexString(bytes[1] & 0x000000ff);
        // 如果是jdk序列化后的
        if ("ACED".equals(protocol.toUpperCase())) {
            System.out.println(protocol.toUpperCase());
        }
    }


    @Test
    public void test123(){
        redisTemplate.opsForValue().set("demo1",new Demo1());

    }
}
