package com.gupaoedu.vip.distributed.io.prestudy;

import java.io.*;

public class WriteIoDemo1 {
    /*字节流*/
    public static void main(String[] args) {
//        String rootPath = WriteIoDemo1.class.getClassLoader().getResource("").getPath();
        String rootPath = WriteIoDemo1.class.getResource("").getPath();
        File file = new File(rootPath+"\\testio.txt");
        OutputStream outputStream=null;
        try {
            outputStream = new FileOutputStream(file);
            outputStream.write("社会主义好".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream!=null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
