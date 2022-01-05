package com.gupaoedu.vip.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

/**
 * 详见简书《Zookeeper客户端Curator使用详解》 https://www.jianshu.com/p/70151fc0ef5d/
 *     CSDN《zookeeper开源客户端Curator介绍(六)》系列文章
 * 这里代码设置监听，而在shell里改变数据去触发事件
 */
public class TestWatcher {
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.builder()
                .connectString("192.168.1.245:2181,192.168.1.245:2182,192.168.1.245:2183")
                .sessionTimeoutMs(4000)
                .retryPolicy(retryPolicy)
                .namespace("testspace2")
                .build();

        curatorFramework.start();
       /* Stat stat = new Stat();
        byte[] bytes = curatorFramework.getData().storingStatIn(stat).forPath("/test1");
        System.out.println(new String(bytes));*/
        //当前节点创建、删除监听  ？？第三参dataIsCompressed是什么意思
        /*final NodeCache nodeCache = new NodeCache(curatorFramework,"/test1",false);
        NodeCacheListener nodeCacheListener = new NodeCacheListener() {
            public void nodeChanged() throws Exception {
                System.out.println("事件触发："+nodeCache.getCurrentData().getPath());
                System.out.println("事件触发："+nodeCache.getCurrentData().getData());
                System.out.println("事件触发："+nodeCache.getCurrentData().getData().length);
            }
        };
        nodeCache.getListenable().addListener(nodeCacheListener);
        nodeCache.start();*/

        //子节点增删改监听
        /*final PathChildrenCache pathChildrenCache = new PathChildrenCache(curatorFramework, "/testParent1/testChild1", false);
        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                System.out.println(pathChildrenCacheEvent.getData());
                System.out.println(pathChildrenCacheEvent.getType());
            }
        };
        pathChildrenCache.getListenable().addListener(pathChildrenCacheListener);
        pathChildrenCache.start();*/

        //综合监听
        /*TreeCache treeCache = new TreeCache(curatorFramework,"/testParent1/testChild1");
        TreeCacheListener treeCacheListener = new TreeCacheListener() {
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
                System.out.println(treeCacheEvent.getData());
                System.out.println(treeCacheEvent.getType());
            }
        };
        treeCache.getListenable().addListener(treeCacheListener);
        treeCache.start();*/

        System.in.read();
    }
}
