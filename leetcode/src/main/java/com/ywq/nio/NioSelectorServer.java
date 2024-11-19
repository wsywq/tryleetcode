package com.ywq.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioSelectorServer {
    public static void main(String[] args) throws IOException {
        // 创建一个ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9000));
        // 配置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 创建Selector
        Selector selector = Selector.open();
        // 注册到Selector上，监听OP_ACCEPT事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("注册成功");

        while (true) {
            // 阻塞等待事件发生
            selector.select();
            //获取Selector中注册的全部事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    // 处理OP_ACCEPT事件
                    ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel accept = server.accept();
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ);
                    System.out.println("连接成功");
                } else if (selectionKey.isReadable()) {
                    SocketChannel server = (SocketChannel) selectionKey.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(6);
                    int read = server.read(byteBuffer);
                    if (read > 0) {
                        System.out.println("接受消息" + new String(byteBuffer.array()));
                    } else {
                        System.out.println("断开连接");
                        server.close();
                    }
                }
                iterator.remove();
            }

        }
    }
}
