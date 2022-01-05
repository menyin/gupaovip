package com.gupaoedu.vip.distributed.io.prestudy;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class FileDemo1 {
    /*File类*/
    public static void main(String[] args) throws IOException {
        /*
        //基本使用
        String rootPath = FileDemo1.class.getResource("").getPath();
        File file = new File(rootPath);
        System.out.println(file.getName());
        System.out.println(file.isFile());
        System.out.println("-----------");
        for (String s : file.list()) {
            System.out.println(s);
        }
        System.out.println("+++++++");
        for (File file1 : file.listFiles()) {
            System.out.println(file1.getName());
        }
        System.out.println(">>>>>>>>>>>");*/
       /* String rootPath = FileDemo1.class.getResource("").getPath();
        File file = new File(rootPath + "\\xyz\\abc.txt");
        *//*try {
            boolean newFile = file.createNewFile();
            System.out.println(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }*//*

        boolean mkdirs = file.mkdirs();
        System.out.println(mkdirs);*/

        File file = new File("mmm.txt");
        boolean newFile = file.createNewFile();
        System.out.println(newFile);
        System.out.println(file.getAbsolutePath());

    }
}
