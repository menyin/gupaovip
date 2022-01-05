package com.gupaoedu.vip.distributed.io.nettydemo.v0;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class NettyServer {
    private static final String DEFAULT_IP = "127.0.0.1";
    private static final int DEFAULT_PORT = 7777;
    private static final int BOSS_NTHREADS = Runtime.getRuntime().availableProcessors()*2;
    private static final int WORK_NTHREADS = 100;
    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BOSS_NTHREADS);//主线程组，相当于nio里的mianReactor
    private static final EventLoopGroup workGroup = new NioEventLoopGroup(WORK_NTHREADS);//工作线程组，相当于nio里的subReactor

    public static void main(String[] args) {
        try {
            start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void start() throws InterruptedException {
        start(DEFAULT_IP, DEFAULT_PORT);
    }

    private static void start(String ip, int port) throws InterruptedException {
        ServerBootstrap serverBootstrap = initServerBootstrap();//ServerBootstrap为服务端工具类
        ChannelFuture channelFuture = serverBootstrap.bind(ip, port).sync();//sync()是以同步的方式，即在bind()完成前此代码会阻塞
        System.out.println("服务端已启动....");
        //注意此处的closeFuture()是注册关闭future的事件，而不是关闭future操作。即在future被关闭前此代码会阻塞
        //closeFuture()其实是返回了一个promise对象，相当于一个钩子。通过promise可以接收事件参数并做业务处理
        channelFuture.channel().closeFuture().sync();
        System.out.println("服务端关闭....");
    }

    private static ServerBootstrap initServerBootstrap() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workGroup)
        .channel(NioServerSocketChannel.class)
        .childHandler(new ChannelInitializer<Channel>() {//这些初始化的内容也可以在serverBootstrap.option()/childOption()方法里初始化。另外.attr()/.childAttr()也可以设置服务端和客户端的一些属性
            protected void initChannel(Channel channel) throws Exception {
                ChannelPipeline pipeline = channel.pipeline();//pipeline就是一个channelHandler的容器，是一个双向链表的结构
                //以下是在pipeline尾部添加handler
                pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                pipeline.addLast(new TcpServerHandler());
            }
        });
        return serverBootstrap;
    }

    public static void shutdown(){
        //？？此处需要关注如何优雅的关闭服务
        workGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }
}
