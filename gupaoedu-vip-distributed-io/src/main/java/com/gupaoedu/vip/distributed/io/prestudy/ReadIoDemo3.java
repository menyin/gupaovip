package com.gupaoedu.vip.distributed.io.prestudy;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadIoDemo3 {
    public static void main(String[] args) {
        String rootPath = ReadIoDemo3.class.getResource("").getPath();
        FileReader fileReader=null;
        BufferedReader bufferedReader=null;
        try {
            fileReader = new FileReader(rootPath+"abc.txt");
            bufferedReader = new BufferedReader(fileReader);
            String line=null;
            while ((line=bufferedReader.readLine() )!= null) {
                System.out.println(line);
            }
            int i=1/0;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //如果未关闭流则资源占用，无法删除abc.txt
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
