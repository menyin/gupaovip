package com.gupaoedu.vip.redis;

import redis.clients.jedis.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * jedis 分片模式（Shard）
 * 详见 https://www.cnblogs.com/vhua/p/redis_1.html
 */
public class JedisDemo3 {
    public static void main(String[] args) throws IOException {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(2);
        poolConfig.setMaxIdle(1);
        poolConfig.setMaxWaitMillis(2000);
        poolConfig.setTestOnBorrow(false);
        poolConfig.setTestOnReturn(false);

        //设置Redis信息
        String host = "192.168.1.237";
        JedisShardInfo shardInfo1 = new JedisShardInfo(host, 7001, 500);
//        shardInfo1.setPassword("test123");
        JedisShardInfo shardInfo2 = new JedisShardInfo(host, 7002, 500);
//        shardInfo2.setPassword("test123");
        JedisShardInfo shardInfo3 = new JedisShardInfo(host, 7004, 500);
//        shardInfo3.setPassword("test123");

        //初始化ShardedJedisPool
        List<JedisShardInfo> infoList = Arrays.asList(shardInfo1, shardInfo2, shardInfo3);
        ShardedJedisPool jedisPool = new ShardedJedisPool(poolConfig, infoList); //直接使用分片模式下的连接池

        //进行查询等其他操作
        ShardedJedis jedis = null;
        try {
            jedis = jedisPool.getResource();
           /* jedis.set("test", "test");
            jedis.set("test1", "test1");*/
            //jedis.set("b", "789");
            String test = jedis.get("b");
            System.out.println(test);
        } finally {
            //使用后一定关闭，还给连接池
            if (jedis != null) {
                jedis.close();
            }
        }
        //在系统退出后关闭连接池
        //jedisPool.close();
    }
}
