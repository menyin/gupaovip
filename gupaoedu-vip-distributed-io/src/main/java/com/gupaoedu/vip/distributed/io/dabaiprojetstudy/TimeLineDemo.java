package com.gupaoedu.vip.distributed.io.dabaiprojetstudy;

public class TimeLineDemo {
    public static void main(String[] args) {
        TimeLine timeLine = new TimeLine();
        System.out.println("*******执行业务逻辑1*********");
        timeLine.begin("bussines1");
        System.out.println("*******执行业务逻辑2*********");
        timeLine.addTimePoint("bussines2");
        System.out.println("*******执行业务逻辑3*********");
        Object[] timePoints = timeLine.failureEnd().getTimePoints();
        System.out.println("*******结束********");
//        timeLine.successEnd();

    }
}
