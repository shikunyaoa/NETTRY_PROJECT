package com.sky.netty.nio.channel;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * @author sky
 * @description: fileChannel:文件拷贝
 * @date: 2021-03-09
 */
public class NioFileChannel04 {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\asus\\Desktop\\01.txt");
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel channel = inputStream.getChannel();

        File file1 = new File("C:\\Users\\asus\\Desktop\\02.txt");
        FileOutputStream outputStream = new FileOutputStream(file1);
        FileChannel channel1 = outputStream.getChannel();

        channel1.transferFrom(channel, 0, channel.size());

        channel.close();
        channel1.close();
        inputStream.close();
        outputStream.close();
    }
}
