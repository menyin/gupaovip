package com.gupaoedu.vip.pattern.observe.subject;

import com.gupaoedu.vip.pattern.observe.core.Event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UnitTest {
    private static  Subject subject = new Subject();
    private static Observe observe = new Observe();
    static {//准备工作
        try {
            Method onSubjectAdd = observe.getClass().getMethod("onSubjectAdd", Event.class);
            Method onSubjectDelete = observe.getClass().getMethod("onSubjectDelete", Event.class);
            Method onSubjectSelect = observe.getClass().getMethod("onSubjectSelect", Event.class);
            //注册事件
            subject.addEventListener(SubjectEventType.ON_ADD, observe, onSubjectAdd);
            subject.addEventListener(SubjectEventType.ON_DELETE, observe, onSubjectDelete);
            subject.addEventListener(SubjectEventType.ON_SELECT, observe, onSubjectSelect);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try {
            subject.add();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
