package com.gupaoedu.vip.pattern.prototype;

import java.io.Serializable;

public class Person implements Cloneable,Serializable{
    private String name;
    private Integer age;
    private Shool shool;

    public Person() {}

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Person(String name, Integer age, Shool shool) {
        this.name = name;
        this.age = age;
        this.shool = shool;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Shool getShool() {
        return shool;
    }

    public void setShool(Shool shool) {
        this.shool = shool;
    }


}
