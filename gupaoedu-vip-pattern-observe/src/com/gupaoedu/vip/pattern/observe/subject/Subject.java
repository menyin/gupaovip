package com.gupaoedu.vip.pattern.observe.subject;

import com.gupaoedu.vip.pattern.observe.core.EventListener;

import java.lang.reflect.InvocationTargetException;

public class Subject extends EventListener {
    public void add() throws InvocationTargetException, IllegalAccessException {
        System.out.println("增加一个对象");
        this.triggerEvent(SubjectEventType.ON_ADD);//此处的触发事件代码一般用代理（AOP）进行注入，保证这些附加代码不和业务代码混合
    }
    public void delete() throws InvocationTargetException, IllegalAccessException {
        System.out.println("删除一个对象");
        this.triggerEvent(SubjectEventType.ON_DELETE);

    }
    public void select() throws InvocationTargetException, IllegalAccessException {
        System.out.println("查找一个对象");
        this.triggerEvent(SubjectEventType.ON_SELECT);

    }
}
