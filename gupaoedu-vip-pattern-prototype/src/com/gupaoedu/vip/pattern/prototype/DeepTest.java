package com.gupaoedu.vip.pattern.prototype;

import java.io.*;

public class DeepTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        Shool s1 = new Shool("清华", 1);
        Person p1 = new Person("张三", 18, s1);
        objectOutputStream.writeObject(p1);
        objectOutputStream.flush();
        objectOutputStream.close();
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        Person p2 = (Person)objectInputStream.readObject();
        System.out.println(p1.getShool()==p2.getShool());
    }
}
