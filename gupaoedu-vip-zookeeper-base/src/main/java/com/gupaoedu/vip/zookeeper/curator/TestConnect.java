package com.gupaoedu.vip.zookeeper.curator;


import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.transaction.CuratorTransactionBridge;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * 详见简书《Zookeeper客户端Curator使用详解》 https://www.jianshu.com/p/70151fc0ef5d/
 *     CSDN《zookeeper开源客户端Curator介绍(六)》系列文章
 */
public class TestConnect {
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("192.168.1.245:2181,192.168.1.245:2182,192.168.1.245:2183")
                .sessionTimeoutMs(4000)
                .retryPolicy(retryPolicy)
                .namespace("testspace1").build();
        curatorFramework.start();

        //增加节点
        /*curatorFramework.create().creatingParentsIfNeeded()//顺带创建所需的父节点
                .withMode(CreateMode.PERSISTENT)
                .forPath("/testParent1/testChild1","人生路漫漫".getBytes());*/

        //查询节点
        /*Stat stat = new Stat();
        byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath("/testParent1/testChild1");
        System.out.println(new String(bytes));*/

        //删除节点，删除节点及其子节点
        /*curatorFramework.delete().deletingChildrenIfNeeded()//如有子节点顺便删除子节点
        .forPath("/testParent1");*/

        //修改节点
        /*curatorFramework.setData().forPath("/", "一切随风".getBytes());*/
        //带版本号的修改节点
        //curatorFramework.setData().withVersion(stat.getVersion()).forPath("/", "一切随风".getBytes());
        curatorFramework.close();
    }
}
