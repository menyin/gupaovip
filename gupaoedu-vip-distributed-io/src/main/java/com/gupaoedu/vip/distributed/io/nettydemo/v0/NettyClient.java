package com.gupaoedu.vip.distributed.io.nettydemo.v0;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class NettyClient {
    private static final String DEFAULT_IP = "127.0.0.1";
    private static final int DEFAULT_PORT = 7777;
    public static void main(String[] args) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        new NettyClient().start();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"client-thread-A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new NettyClient().start();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"client-thread-B").start();

    }
    public void start() throws InterruptedException {
        start(DEFAULT_IP, DEFAULT_PORT);
    }
    public void start(String ip,int port) throws InterruptedException {
        Bootstrap bootstrap=initBootstrap();
        for (int i = 0; i < 3; i++) {//一个线程发3次
            ChannelFuture channelFuture = bootstrap.connect(ip, port).sync();
            channelFuture.channel().writeAndFlush("hi 你好服务端，我是"+Thread.currentThread().getName()+"，序号："+i);
            channelFuture.channel().closeFuture().sync();
        }
    }

    private Bootstrap initBootstrap() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
        .channel(NioSocketChannel.class)
        .option(ChannelOption.TCP_NODELAY,true)
        .handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                ChannelPipeline pipeline = socketChannel.pipeline();
                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                pipeline.addLast("handler", new TcpClientHandler());
            }
        });
        return bootstrap;
    }
}
