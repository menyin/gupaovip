package com.gupaoedu.vip.zookeeper.curator.liondemo;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class TestTransation {
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("192.168.1.245:2181,192.168.1.245:2182,192.168.1.245:2183")
                .sessionTimeoutMs(4000)
                .retryPolicy(retryPolicy)
                .namespace("testspace2")
                .build();
        curatorFramework.start();

        curatorFramework.inTransaction()//开启事务
                .check().forPath("path")
                .and() //通过and()连接每个操作
                .create().withMode(CreateMode.EPHEMERAL).forPath("path", "data".getBytes())
                .and()
                .setData().withVersion(10086).forPath("path", "data2".getBytes())
                .and()
                .commit();


    }
}
