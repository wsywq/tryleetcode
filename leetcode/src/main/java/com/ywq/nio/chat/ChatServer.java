package com.ywq.nio.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ChatServer {
    public static void main(String[] args) throws Exception {
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerLoopGroup = new NioEventLoopGroup(8);
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossLoopGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler((new ChannelInitializer<NioServerSocketChannel>() {
                      @Override
                      protected void initChannel(NioServerSocketChannel ch) throws Exception {
                          ChannelPipeline pipeline = ch.pipeline();
                          pipeline.addLast("decoder",new StringDecoder());
                          pipeline.addLast("encoder",new StringEncoder());
                          pipeline.addLast(new ChatServerHandler());
                      }
                    }));
            ChannelFuture channelFuture = serverBootstrap.bind(9000).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossLoopGroup.shutdownGracefully();
            workerLoopGroup.shutdownGracefully();
        }

    }
}
