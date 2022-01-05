package com.gupaoedu.vip.distributed.io.biodemo;

import java.io.IOException;

public class BioTest {
    public static void main(String[] args) throws InterruptedException, IOException {
        new Thread(new Runnable() {
            public void run() {
                try {
                    BioServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        },"BioServer").start();

        Thread.sleep(1000);

        new Thread(new Runnable() {
            public void run() {
                try {
                    BioClient.send();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        },"BioClient").start();
        System.in.read();
    }
}
