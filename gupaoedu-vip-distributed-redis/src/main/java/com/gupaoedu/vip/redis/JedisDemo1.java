package com.gupaoedu.vip.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * jedis单机模式
 * 详见：淘淘商城测试demo
 */
public class JedisDemo1 {
    public static void main(String[] args) {
       /* *//*普通单机版*//*
        //创建一个jedis对象，需要指定服务的ip和端口号
        Jedis jedis = new Jedis("192.168.1.237", 7004);
        //直接操作数据库
        System.out.println(jedis.get("b"));
        //关闭jedis
        jedis.close();

        *//*普通单机连接池版*//*
        //创建一个数据库连接池对象（单例），需要指定服务的ip和端口号
        JedisPool jedisPool = new JedisPool("192.168.1.237", 7004);
        //从连接池中获得连接
        Jedis poolJedis = jedisPool.getResource();
        //使用Jedis操作数据库（方法级别使用）
        System.out.println(poolJedis.get("b"));
        //一定要关闭Jedis连接
        jedis.close();
        //系统关闭前关闭连接池
        jedisPool.close();*/


        int i=1;
        System.out.println((i++)+(++i));
    }
}
