package com.gupaoedu.vip.zookeeper.distributedlock;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 该分布式锁主要实现lock方法的功能逻辑，以展示分布式锁的基本原理
 */
public class DistributedLock implements Lock, Watcher {
    private ZooKeeper zooKeeper = null;
    private String ROOT_LOCK = "/disLock";//定义这个分布式锁的zookeeper根节点
    private String CURRENT_LOCK;//当前客户端获得的锁节点，是一个临时有序节点
    private String WAIT_LOCK;//当前应用客户端获得锁节点的前一个锁节点名
    private CountDownLatch countDownLatch = null;

    public DistributedLock() {
        try {
            zooKeeper = new ZooKeeper("192.168.1.245:2181,192.168.1.245:2182,192.168.1.245:2183", 8000, this);
            Stat exists = zooKeeper.exists(ROOT_LOCK, false);//此处不能再设置监听了,否则总监听会出问题
            if (exists == null) {
                String s = zooKeeper.create(ROOT_LOCK, "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public void lock() {
        if (tryLock()) {
            System.out.println(Thread.currentThread().getName() + " 成功获得锁 "+CURRENT_LOCK);
            return;
        }

        try {
            Stat exists = zooKeeper.exists(WAIT_LOCK, true);//对当前节点的前一个节点的增删进行监听，事件写在this.process方法
            System.out.println(Thread.currentThread().getName() + " 正在等待锁 "+WAIT_LOCK);

            if (exists != null) {
                countDownLatch = new CountDownLatch(1);
                countDownLatch.await();
                System.out.println(Thread.currentThread().getName()+" 得到了锁 "+CURRENT_LOCK);//在事件里根据逻辑将countDownLatch计数器+1了
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }

    }

    public void lockInterruptibly() throws InterruptedException {

    }

    public boolean tryLock() {
        try {
            CURRENT_LOCK = zooKeeper.create(ROOT_LOCK + "/", "0".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            List<String> childrens = zooKeeper.getChildren(ROOT_LOCK, false);
            SortedSet<String> existNodes = new TreeSet<String>();
            for (String children : childrens) {
                existNodes.add(ROOT_LOCK + "/" + children);
            }
            String firstNode = existNodes.first();
            if (firstNode.equals(CURRENT_LOCK)) {
                return true;
            }
            SortedSet<String> currentNodePreNodes = ((TreeSet<String>) existNodes).headSet(CURRENT_LOCK);//获取当前节点之前的所有节点
            WAIT_LOCK = currentNodePreNodes.last();//设置当前节点的前一个节点
            return false;

        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public void unlock() {
        System.out.println(Thread.currentThread().getName()+"->释放锁"+CURRENT_LOCK);
        try {
            zooKeeper.delete(CURRENT_LOCK,-1);
            CURRENT_LOCK=null;
            zooKeeper.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }
    }

    public Condition newCondition() {
        return null;
    }

    public void process(WatchedEvent watchedEvent) {
        String eventPath = watchedEvent.getPath();
        Event.EventType type = watchedEvent.getType();
        if (watchedEvent!=null&&true) {//如果是WAIT_LOCK节点并且是删除节点事件，则进行获取锁(其实是将计数器+1以使得阻塞解除)
            List<String> childrens = null;
            try {
                childrens = zooKeeper.getChildren(ROOT_LOCK, false);

            SortedSet<String> existNodes = new TreeSet<String>();
            for (String children : childrens) {
                existNodes.add(ROOT_LOCK + "/" + children);
            }
            String firstNode = existNodes.first();
            if (firstNode.equals(CURRENT_LOCK)) {
                countDownLatch.countDown();//当最小节点等于当前节点时，则将计数器+1
            }
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
