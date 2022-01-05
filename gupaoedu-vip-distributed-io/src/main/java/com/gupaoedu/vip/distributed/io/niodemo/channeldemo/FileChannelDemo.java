package com.gupaoedu.vip.distributed.io.niodemo.channeldemo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelDemo {
    public static void main(String[] args) {
        String rootPath = FileChannelDemo.class.getResource("").getPath();
        FileOutputStream fileOutputStream=null;
        FileChannel channel=null;
        try {
            fileOutputStream = new FileOutputStream(rootPath+"testChannel.txt");
            channel = fileOutputStream.getChannel();
            ByteBuffer buff = ByteBuffer.allocate(50);
            buff.put("社会主义好".getBytes());
            buff.flip();
            channel.write(buff);
            buff.clear();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (channel != null) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
