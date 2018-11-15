package com.gupaoedu.vip.pattern.singleton.seriable;

import java.io.*;

public class Seriable implements Serializable{
    private static Seriable seriable=null;
    private Seriable(){}
    public static Seriable getInstance(){
        if (seriable == null) {
            seriable=new Seriable();
            try {
                /*File file = new File("/Seriable.obj");
                FileOutputStream fileOutputStream = new FileOutputStream(file);*/
                FileOutputStream fileOutputStream = new FileOutputStream("Seriable.obj");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(seriable);
                objectOutputStream.flush();
                objectOutputStream.close();
                fileOutputStream.flush();
                fileOutputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        try {
/*
            File file = new File("/Seriable.obj");
            FileInputStream fileInputStream = new FileInputStream(file);
*/
            FileInputStream fileInputStream = new FileInputStream("Seriable.obj");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Seriable object = (Seriable)objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /*private Object readResolve() throws ObjectStreamException{
        return seriable;
    }*/
}
