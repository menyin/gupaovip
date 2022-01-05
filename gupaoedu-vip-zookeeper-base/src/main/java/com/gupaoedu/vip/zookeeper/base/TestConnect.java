package com.gupaoedu.vip.zookeeper.base;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

public class TestConnect {
    public static void main(String[] args) throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper("192.168.1.245:2181,192.168.1.245:2182,192.168.1.245:2183",
                8000,//这个超时时间不能设置太短，否则在操作数据时候有可能出现session断开的情况
                new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                if(Event.KeeperState.SyncConnected==watchedEvent.getState()){
                    //如果收到了服务端的响应事件，连接成功
                    try {
                        countDownLatch.countDown();
                        System.out.println("来啦，客户端，连接了~~");
                        System.out.println(watchedEvent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        });
        countDownLatch.await();//countDownLatch有点像loadrunner里的集合点
        System.out.println(zooKeeper.getState());

        //增加节点
       /* String s = zooKeeper.create(
                "/test2",//节点名称
                "tt2".getBytes(), //节点值
                ZooDefs.Ids.OPEN_ACL_UNSAFE, //节点权限，这里是全开放,不安全的一种方式
                CreateMode.PERSISTENT//节点类型，这里是持久化节点
        );
        System.out.println(s);*/

        //查询节点
        Stat stat = new Stat();
        byte[] data = zooKeeper.getData("/test2", false, stat);
        System.out.println(new String(data));
        System.out.println("版本号："+stat.getVersion());

        //修改节点
        /*Stat updateStat = zooKeeper.setData("/test2", "456".getBytes(), stat.getVersion());//在sessionTimeout设置过小的时候回出错
        System.out.println(updateStat.getVersion());*/

        //删除节点
//        zooKeeper.delete("/test2",stat.getVersion());

        //
        /*List<String> children = zooKeeper.getChildren("/test2", false);
        for (String child : children) {
            System.out.println(child);
        }*/

    }

}
