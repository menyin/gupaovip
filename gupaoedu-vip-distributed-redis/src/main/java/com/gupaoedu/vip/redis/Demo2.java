package com.gupaoedu.vip.redis;

import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.List;

/**
 * spring-data-redis JedisConnectionFactory的集群版使用
 */
public class Demo2 {
    public static void main(String[] args) {

        //集群节点配置
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        List<RedisNode> redisNodes = Arrays.asList(
                new RedisNode("192.168.1.237", 7001),
                new RedisNode("192.168.1.237", 7002),
                new RedisNode("192.168.1.237", 7003),
                new RedisNode("192.168.1.237", 7004),
                new RedisNode("192.168.1.237", 7005),
                new RedisNode("192.168.1.237", 7006)
        );
        redisClusterConfiguration.setClusterNodes(redisNodes);
        //jedis连接池配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(1000);
        jedisPoolConfig.setMaxIdle(1000);
        jedisPoolConfig.setMaxWaitMillis(1000);

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration);
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        //******这个是spring bean初始化时调用的初始化方法，也是一个初始化方法 ******
        jedisConnectionFactory.afterPropertiesSet();

        //cluster实例用jedisCommands接口以便调用其方法
        /*RedisClusterConnection connection = jedisConnectionFactory.getClusterConnection();
        System.out.println(new String(connection.get("b".getBytes())));*/
        //用这个就不用做byte[]转换了
        /*JedisCommands connection = (JedisCommands)jedisConnectionFactory.getClusterConnection().getNativeConnection();
        System.out.println(connection.get("b"));*/
        JedisCommands connection = (JedisCommands) jedisConnectionFactory.getConnection().getNativeConnection();
        System.out.println(connection.get("b"));



    }


}
