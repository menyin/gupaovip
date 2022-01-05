package com.gupaoedu.vip.redis;

import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.BoundGeoOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;

/**
 * spring-data-redis JedisConnectionFactory的单机版使用
 */
public class Demo1 {
    public static void main(String[] args) throws Exception {
        //JedisConnectionFactory1.x 过时使用方式
        /*JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setPort(7004);
        jedisConnectionFactory.setHostName("192.168.1.237");
        Jedis jedis = (Jedis)jedisConnectionFactory.getConnection().getNativeConnection();
        System.out.println(jedis.get("b"));
        jedis.close();*/

        //JedisConnectionFactory2.x 使用方式1
        /*RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("192.168.1.237");
        redisStandaloneConfiguration.setDatabase(0);
        redisStandaloneConfiguration.setPort(7004);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
        Jedis jedis = (Jedis)jedisConnectionFactory.getConnection().getNativeConnection();
        System.out.println(jedis.get("b"));
        jedis.close(); */

        //JedisConnectionFactory2.x 使用方式2
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();

        redisStandaloneConfiguration.setHostName("192.168.1.237");
        redisStandaloneConfiguration.setDatabase(0);
        redisStandaloneConfiguration.setPort(7004);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(jedisConnectionFactory);
        String b = stringRedisTemplate.opsForValue().get("b");
        System.out.println(b);
    }
}
