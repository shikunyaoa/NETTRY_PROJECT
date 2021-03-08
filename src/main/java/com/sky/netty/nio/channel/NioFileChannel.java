package com.sky.netty.nio.channel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author sky
 * @description: 文件通道
 * @date: 2021-03-08
 */
public class NioFileChannel {

    public static void main(String[] args) throws IOException {
        String str = "我爱Java";
        FileOutputStream output = new FileOutputStream("C:\\Users\\asus\\Desktop\\file.txt");
        FileChannel channel = output.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(str.getBytes());
        //重置buffer中的相关变量
        buffer.flip();
        //从buffer中向通道中写数据
        channel.write(buffer);
        channel.close();
    }
}
