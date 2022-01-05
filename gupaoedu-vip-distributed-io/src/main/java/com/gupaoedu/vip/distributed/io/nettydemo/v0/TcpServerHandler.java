package com.gupaoedu.vip.distributed.io.nettydemo.v0;

import io.netty.channel.*;
import io.netty.util.concurrent.EventExecutorGroup;

public class TcpServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //一个客户端连接一次，通道就激活一次
        System.out.println("通道已激活>>>>>>>");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {//ChannelContext
        System.out.println("服务端接收到客户端接收信息:" + msg);
        ctx.channel().writeAndFlush("hello 你好客户端，你是说" + msg + "吗？");
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        System.out.println("get server exception :"+cause.getMessage());
    }
}
