package com.gupaoedu.vip.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.io.Closeable;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class JedisDemo6 {
    static  JedisCluster jedisCluster=null;
    static{
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
    public static void main(String[] args) {
//     case0();
//       case1();
       case2();
    }

    /***
     * 用byte[]的key存取值
     */
    public static void case0(){
        byte[] hello =getBytesKey("hello");
        byte[] helloF1 = getBytesKey("helloF1");
        byte[] str123 = getBytesKey("str123");
        jedisCluster.hset(hello,helloF1,str123);
        System.out.println(jedisCluster.hget(hello, helloF1));
        System.out.println(jedisCluster.hget(hello, helloF1));

    }

    /***
     * 用byte[]的key存值  用string的key取值
     */
    public static void case1(){
       /* byte[] hello =getBytesKey("myhash2222");
        byte[] helloF1 = getBytesKey("helloF1");
        byte[] str123 = getBytesKey("str123");
        jedisCluster.hset(hello,helloF1,str123);*/
        System.out.println(jedisCluster.hget("myhash2222", "helloF1"));
    }

    /***
     * JedisCluster集群环境下不支持模糊查询
     * 只能单机模糊查询
     */
    public static void case2(){
        /* *//*普通单机版*/
        //创建一个jedis对象，需要指定服务的ip和端口号
        Jedis jedis = new Jedis("192.168.1.237", 7003);
        Set<String> set = jedis.keys("myhash*");
        for (String key : set) {
            System.out.println(key);
        }
        //关闭jedis
        jedis.close();
    }





    /**
     * 获取byte[]类型Key
     * @param object
     * @return
     */
    public static byte[] getBytesKey(Object object){
        if(object instanceof String){
            return getBytes((String)object);
        }else{
            return null;
//            return ObjectUtils.serialize(object);
        }
    }
    /**
     * 转换为字节数组
     * @param str
     * @return
     */
    public static byte[] getBytes(String str){
        if (str != null){
            try {
                return str.getBytes( "UTF-8");
            } catch (UnsupportedEncodingException e) {
                return null;
            }
        }else{
            return null;
        }
    }


}
