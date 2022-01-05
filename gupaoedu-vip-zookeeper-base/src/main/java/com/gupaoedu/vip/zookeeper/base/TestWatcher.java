package com.gupaoedu.vip.zookeeper.base;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

public class TestWatcher {
    public static void main(String[] args) throws Exception {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = new ZooKeeper(
                "192.168.1.245:2181,192.168.1.245:2182,192.168.1.245:2183",
                8000,//这个超时时间不能设置太短，否则在操作数据时候有可能出现session断开的情况
                new Watcher() {
                    public void process(WatchedEvent watchedEvent) {
                        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
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

        //节点事件
        //getData
        /*Stat stat = new Stat();
        byte[] data = zooKeeper.getData("/test2", new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent);
            }
        }, stat);
        Thread.sleep(6000000);//这里让主线程在此处休眠，然后用shell去修改值就可以看到事件触发了，不过是一次性的。*/

        //exists
       /* Stat exists = zooKeeper.exists("/test2", new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent);
            }
        });
        Thread.sleep(6000000);*/
        zooKeeper.getChildren("/test2", new Watcher() {
            public void process(WatchedEvent watchedEvent) {
                System.out.println(watchedEvent);
            }
        });
        Thread.sleep(6000000);
    }
}
