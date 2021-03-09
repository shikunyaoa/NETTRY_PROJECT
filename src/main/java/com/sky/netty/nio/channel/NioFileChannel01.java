package com.sky.netty.nio.channel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author sky
 * @description: fileChannel:读取文件数据
 * @date: 2021-03-09
 */
public class NioFileChannel01 {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\asus\\Desktop\\file.txt");
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel channel = inputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());
        channel.read(buffer);
        System.out.println(new String(buffer.array()));
        inputStream.close();
    }
}
