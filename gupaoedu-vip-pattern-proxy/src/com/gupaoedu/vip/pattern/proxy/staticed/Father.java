package com.gupaoedu.vip.pattern.proxy.staticed;

public class Father implements Person {
    private Person person;
    public Father(Person person){
        this.person=person;
    }
    @Override
    public void findLove() {
        System.out.println("老爸根据你的要求找对象");
        this.person.findLove();
        System.out.println("找不到对象别赖老爸");

    }

    @Override
    public void findJob() {
        System.out.println("老爸根据你的要求找工作");
        this.person.findJob();
        System.out.println("找不到工作别赖老爸");
    }

    @Override
    public void eat() {
        System.out.println("老爸根据你的要求做的菜");
        this.person.eat();
        System.out.println("没胃口不要怪老爸");
    }
}
