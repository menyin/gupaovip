package com.gupaoedu.vip.distributed.io.prestudy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Mp3Demo1 {
    public static void main(String[] args) {
        splitMp3();
    }
    private static void splitMp3(){
        String rootPath = Mp3Demo1.class.getResource("").getPath();
        FileInputStream fileInputStream=null;
        FileOutputStream fileOutputStream=null;
        try {
            fileInputStream = new FileInputStream(rootPath + "lang.mp3");
            byte[] buff = new byte[1024 * 1024];
            for (int i = 0; i < 10; i++) {
                int len;
                if((len=fileInputStream.read(buff))!=-1){
                    fileOutputStream = new FileOutputStream(rootPath + "lang" + "-" + i + ".mp3");
                    fileOutputStream.write(buff);
                }else {
                    break;
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
