package com.gupaoedu.vip.distributed.io.niodemo.channeldemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
/*
* 此类模拟客户端及其demo
* */
public class SocketChannelDemo implements Runnable {
    private String name;
    private InetSocketAddress inetSocketAddress;

    public SocketChannelDemo(String name, InetSocketAddress inetSocketAddress) {
        this.name = name;
        this.inetSocketAddress = inetSocketAddress;
    }

    public void run() {
        Selector selector=null;
        SocketChannel socketChannel=null;
        try {
            //打开socketchannel通道
            socketChannel = SocketChannel.open();
            //设置非阻塞
            socketChannel.configureBlocking(false);
            //连接指定地址的服务端
            socketChannel.connect(this.inetSocketAddress);
            //注册自己感兴趣的事件,？此处注意逻辑或的意义
            int interestSet = SelectionKey.OP_READ | SelectionKey.OP_WRITE;
            selector = Selector.open();
            socketChannel.register(selector, interestSet, new MyBuffers(128, 128));
            //等待连接
            while (!socketChannel.isConnected()){;}
            System.out.println("已经连接上"+this.inetSocketAddress.getHostName());
        } catch (IOException e) {
            System.out.println("客户端连接失败");
            return;
        }
        //轮询一轮就判断线程状态，如果interrupted了则退出
        while (!Thread.currentThread().isInterrupted()){
            try {
                //阻塞等待
                selector.select();//返回准备就绪的channel数量，如果为0其实可以continute循环
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    //selectionKey是代表和通道相关的一个对象
                    SelectionKey selectionKey = iterator.next();
                    //从selectionKey中获取对应的附件Buffers
                    MyBuffers myBuffers = (MyBuffers)selectionKey.attachment();
                    ByteBuffer readBuffer = myBuffers.getReadBuffer();
                    ByteBuffer writeBuffer = myBuffers.getWriteBuffer();
                    //SelectableChannel是SocketChannel的父类
                    SocketChannel originSocketChannel = (SocketChannel)selectionKey.channel();
                    if (selectionKey.isReadable()) {
                        originSocketChannel.read(readBuffer);
                        readBuffer.flip();
                        byte[] bytes = Arrays.copyOf(readBuffer.array(), readBuffer.limit());
                        //此处 new String()已经有默认的解码
                        System.out.println("客户端端接收到"+this.inetSocketAddress.getHostName()+"的消息："+new String(bytes));
                        readBuffer.clear();
                    }
                    if (selectionKey.isWritable()) {
                        //此处getBytes()已经有默认的编码
                        writeBuffer.put(("你好服务端，我是客户端" + this.name).getBytes());
                        writeBuffer.flip();
                        originSocketChannel.write(writeBuffer);
                        writeBuffer.clear();
                    }
                    Thread.sleep(1000);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (selector != null) {
                        selector.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(new SocketChannelDemo("客户端线程1", new InetSocketAddress(7777)));
        thread1.start();
    }
}
