package com.gupaoedu.vip.redis;

import redis.clients.jedis.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * jedis 哨兵连接池   * 此demo要启用哨兵功能才能测试
 * 一般用分片就不用哨兵监控
 * 详见 https://www.cnblogs.com/xujishou/p/6511111.html
 */
public class JedisDemo4 {
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
        Set<String> sentinels = new HashSet<String>();
        sentinels.add("192.168.1.237:7001");
        sentinels.add("192.168.1.237:7002");
        sentinels.add("192.168.1.237:7003");
        String masterName="mymaster"; //** masterName是在哨兵功能启动时的sentinel.conf里配置的
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool(masterName, sentinels, poolConfig, 1000);
        //进行查询等其他操作
        Jedis jedis = null;
        try {
            jedis = jedisSentinelPool.getResource();
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
