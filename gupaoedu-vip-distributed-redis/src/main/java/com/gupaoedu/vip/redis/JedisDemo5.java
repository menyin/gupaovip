package com.gupaoedu.vip.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPubSub;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

/**
 * 测试重复订阅消息，会不会重复接收消息
 * 经过测试结论如下：
 * 1.在第一次订阅[等待接收消息]如果阻塞了第二次订阅，重复订阅并不会重复接收到消息。因为第二次其实没有订阅到
 * 2.在第一次订阅[等待接收消息]如果没有阻塞第二次订阅，重复订阅会重复接收到消息。因为第二次其实有订阅到
 */
public class JedisDemo5 {
    static JedisCluster jedisCluster = null;

    static {
        //创建一个JedisCluster对象，构造参数Set类型，集合中每个元素是HostAndPort类型
        Set<HostAndPort> nodes = new HashSet<HostAndPort>();
        //向集合中添加节点
        nodes.add(new HostAndPort("192.168.1.237", 7001));
        nodes.add(new HostAndPort("192.168.1.237", 7002));
        nodes.add(new HostAndPort("192.168.1.237", 7003));
        nodes.add(new HostAndPort("192.168.1.237", 7004));
        nodes.add(new HostAndPort("192.168.1.237", 7005));
        nodes.add(new HostAndPort("192.168.1.237", 7006));
        jedisCluster = new JedisCluster(nodes);
    }


    public static void main(String[] args) throws IOException {
        case1();
//        case2();
    }


    public static void case1() throws IOException {
        //直接使用JedisCluster操作redis，自带连接池。jedisCluster对象可以是单例 的。
        jedisCluster.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("第二次订阅#channel:" + channel + "=== message:" + message);
            }
        }, "mytopic");

        new Thread(new Runnable() {
            public void run() {
                jedisCluster.subscribe(new JedisPubSub() {
                    @Override
                    public void onMessage(String channel, String message) {
                        System.out.println("第一次订阅#channel:" + channel + "=== message:" + message);
                    }
                }, "mytopic");
            }
        }).start();

        //系统关闭前关闭JedisCluster
        jedisCluster.close();
    }

    public static void case2() throws IOException {
        //直接使用JedisCluster操作redis，自带连接池。jedisCluster对象可以是单例 的。
        new Thread(new Runnable() {
            public void run() {
                jedisCluster.subscribe(new JedisPubSub() {
                    @Override
                    public void onMessage(String channel, String message) {
                        System.out.println("第一次订阅#channel:" + channel + "=== message:" + message);
                    }
                }, "mytopic");
            }
        }).start();
        jedisCluster.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println("第二次订阅#channel:" + channel + "=== message:" + message);
            }
        }, "mytopic");

        //系统关闭前关闭JedisCluster
        jedisCluster.close();
    }


}
