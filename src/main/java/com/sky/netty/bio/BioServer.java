package com.sky.netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author sky
 * @description: bio(阻塞io)实例
 * @date: 2021-03-01
 */
public class BioServer {

    public static void main(String[] args) throws IOException {

        ExecutorService pool = Executors.newCachedThreadPool();

        ServerSocket server = new ServerSocket(6666);
        while(true){
            final Socket socket = server.accept();
            System.out.println("连接到一个客户端");
            pool.execute(() -> handler(socket));
        }
    }

    public static void handler(Socket socket){

        //通过socket获取一个输入流
        try {
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while(inputStream.read(bytes) != -1){
                System.out.println(new String(bytes).toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("关闭Client链接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
