package com.sky.netty.nio.channel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author sky
 * @description: fileChannel:从一个文件中读取数据到另一个文件
 * @date: 2021-03-09
 */
public class NioFileChannel02 {

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\asus\\Desktop\\01.txt");
        FileInputStream inputStream = new FileInputStream(file);
        FileChannel channel = inputStream.getChannel();

        File file1 = new File("C:\\Users\\asus\\Desktop\\02.txt");
        FileOutputStream outputStream = new FileOutputStream(file1);
        FileChannel channel1 = outputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(502);

        while(true){
            //清空byteBuffer相关属性，避免position=limit导致无法退出循环
            byteBuffer.clear();
            int read = channel.read(byteBuffer);
            if(read == -1){
                break;
            }
            byteBuffer.flip();
            channel1.write(byteBuffer);
        }

        inputStream.close();
        outputStream.close();
    }
}
