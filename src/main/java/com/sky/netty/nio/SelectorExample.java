package com.sky.netty.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author sky
 * @description: NIO中selector(选择器:用于使用单个线程处理多个通道)
 * @date: 2021-03-02
 */
public class SelectorExample {

    public static void main(String[] args) throws IOException {

        Selector selector = Selector.open();
        System.out.println("Selector is open for marking connection: " + selector.isOpen());
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 8080);
        serverSocketChannel.bind(hostAddress);
        serverSocketChannel.configureBlocking(false);
        int ops = serverSocketChannel.validOps();
        SelectionKey selectionKey = serverSocketChannel.register(selector, ops, null);
        for(;;){
            System.out.println("Waiting for socket operation");
            int noOfKeys = selector.select();
            Set selectedKeys =  selector.selectedKeys();
            Iterator iterator =  selectedKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey ky = (SelectionKey) iterator.next();
                if(ky.isAcceptable()){
                    SocketChannel client = serverSocketChannel.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);
                    System.out.println("The new connetion is accept from the client: " + client);
                }else if(ky.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) ky.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(256);
                    socketChannel.read(byteBuffer);
                    String output = new String(byteBuffer.array()).trim();
                    System.out.println("Message from client is: " + output);
                    if(output.equals("Bye Bye")){
                        socketChannel.close();
                        System.out.println("The client message are complete; close the session");
                    }
                }
                iterator.remove();
            }
        }
    }
}
