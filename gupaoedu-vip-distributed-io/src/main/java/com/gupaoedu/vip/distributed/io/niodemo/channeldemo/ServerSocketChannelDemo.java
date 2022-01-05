package com.gupaoedu.vip.distributed.io.niodemo.channeldemo;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;

public class ServerSocketChannelDemo implements Runnable {
    private InetSocketAddress inetSocketAddress;

    public ServerSocketChannelDemo(int port) {
        this.inetSocketAddress = new InetSocketAddress(port);
    }

    public void run() {
        ServerSocketChannel serverSocketChannel = null;
        Selector selector = null;
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            //最大缓冲连接数为100
            serverSocketChannel.bind(this.inetSocketAddress, 100);
            selector = Selector.open();
            int interestSet = SelectionKey.OP_ACCEPT;
            serverSocketChannel.register(selector, interestSet);

//            SocketChannel accept = serverSocketChannel.accept();
        } catch (IOException e) {
            System.out.println("服务端启动失败...");
            return;
        }
        System.out.println("服务端已启动...");
        try {
            while (!Thread.currentThread().isInterrupted()) {


                selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    try {
                        if (selectionKey.isAcceptable()) {
                            ServerSocketChannel originServerSocketChannel = (ServerSocketChannel) selectionKey.channel();
                            SocketChannel accept = originServerSocketChannel.accept();
                            accept.configureBlocking(false);
                            int interestSet = SelectionKey.OP_READ;
                            //注意此时是将accept客户端通道注册到selector上，selector上也包含服务端通道也包含客户端通道
                            accept.register(selector, interestSet, new MyBuffers(128, 128));
                            System.out.println("有客户端：" + accept.getRemoteAddress() + " 连接进来...");
                        }
                        if (selectionKey.isReadable()) {

                            MyBuffers myBuffers = (MyBuffers) selectionKey.attachment();
                            ByteBuffer readBuffer = myBuffers.getReadBuffer();
                            //注意：此处一定是从selectionKey对象中获取响应的通道
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            //接收到来自客户端的消息
                            socketChannel.read(readBuffer);
                            readBuffer.flip();
                            byte[] bytes = Arrays.copyOf(readBuffer.array(), readBuffer.limit());
                            String requestMsg = new String(bytes);
                            System.out.println("服务端接收到来自客户端消息：" + requestMsg);
                            readBuffer.rewind();

                            //准备好服务端针对客户端通道要响应的消息（等待可写时写入）
                            ByteBuffer writeBuffer = myBuffers.getWriteBuffer();
                            byte[] resposeMsg = ("针对客户端消息：" + requestMsg + " 的响应数据为：你好哈，客户端" + socketChannel.getRemoteAddress()).getBytes();
                            writeBuffer.put(resposeMsg);
                            //writeBuffer.flip();
                            //重置该selectionKey该兴趣的事件
                            selectionKey.interestOps(selectionKey.interestOps() | SelectionKey.OP_WRITE);
                        }
                        if (selectionKey.isWritable()) {
                            MyBuffers myBuffers = (MyBuffers) selectionKey.attachment();
                            ByteBuffer writeBuffer = myBuffers.getWriteBuffer();
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            writeBuffer.flip();
                            //socketChannel.write(writeBuffer);
                            //？？这段代码是什么意思-----begin
                            //参考csdn收藏《java nio Buffer 中 compact的作用》
                            int len = 0;
                            while (writeBuffer.hasRemaining()) {
                                len = socketChannel.write(writeBuffer);
                                if (len == 0) {
                                    break;
                                }
                            }
                            writeBuffer.compact();//压缩缓冲区

                            if (len != 0) {
                                selectionKey.interestOps(selectionKey.interestOps() & (~SelectionKey.OP_WRITE));
                            }

                            //？？这段代码是什么意思-----end

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        selectionKey.cancel();
                        try {
                            selectionKey.channel().close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                selector.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }
}
