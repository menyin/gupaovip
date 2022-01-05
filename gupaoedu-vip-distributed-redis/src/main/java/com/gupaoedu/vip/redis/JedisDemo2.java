package com.gupaoedu.vip.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
/**
 * jedis Cluster模式
 * 详见：淘淘商城测试demo
 */
public class JedisDemo2 {
    public static void main(String[] args) throws IOException {

        //创建一个JedisCluster对象，构造参数Set类型，集合中每个元素是HostAndPort类型
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        //向集合中添加节点
        nodes.add(new HostAndPort("192.168.1.237", 7001));
        nodes.add(new HostAndPort("192.168.1.237", 7002));
        nodes.add(new HostAndPort("192.168.1.237", 7003));
        nodes.add(new HostAndPort("192.168.1.237", 7004));
        nodes.add(new HostAndPort("192.168.1.237", 7005));
        nodes.add(new HostAndPort("192.168.1.237", 7006));
        JedisCluster jedisCluster = new JedisCluster(nodes);
        //直接使用JedisCluster操作redis，自带连接池。jedisCluster对象可以是单例 的。
        jedisCluster.set("cluster-test", "hello jedis cluster");
//        String string = jedisCluster.get("cluster-test");
        String string = jedisCluster.get("aa");
        System.out.println(string);
        //系统关闭前关闭JedisCluster
        jedisCluster.close();
    }
}
