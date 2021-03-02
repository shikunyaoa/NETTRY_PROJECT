package com.sky.netty.nio.api;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author sky
 * @description: NIO TransferDemo
 * @date: 2021-03-02
 */
public class TransfertoDemo {

    public static void main(String[] args) throws IOException {

        String relativelyPath = System.getProperty("user.dir");
        String[] IF = new String[]{relativelyPath + "/input1.txt", relativelyPath + "/input2.txt",
        relativelyPath + "/input3.txt", relativelyPath + "/input4.txt"};
        String OF = relativelyPath + "/combine_output.txt";
        WritableByteChannel targetChannel = new FileOutputStream(new File(OF)).getChannel();
        for (int i = 0; i < IF.length; i++) {
            FileInputStream inputStream = new FileInputStream(IF[i]);
            FileChannel inputChannel = inputStream.getChannel();
            inputChannel.transferTo(0, inputChannel.size(), targetChannel);

            inputChannel.close();
            inputStream.close();
        }
        targetChannel.close();
        System.out.println("all jobs done...");
    }
}
