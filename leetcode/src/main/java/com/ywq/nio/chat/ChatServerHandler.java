package com.ywq.nio.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ChatServerHandler  extends SimpleChannelInboundHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        String msg = (String) o;
        System.out.println("收到消息：" + msg);
        channelHandlerContext.writeAndFlush("from server: " + msg);
    }

}
