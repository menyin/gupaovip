package com.gupaoedu.vip.pattern.observe.core;

import java.lang.reflect.Method;

public class Event {
    private Object source;
    private Object target;
    private Method callback;
    private String trigger;
    private long time;

    public Event(Object source, Object target, Method callback, String trigger, long time) {
        this.source = source;
        this.target = target;
        this.callback = callback;
        this.trigger = trigger;
        this.time = time;
    }

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Method getCallback() {
        return callback;
    }

    public void setCallback(Method callback) {
        this.callback = callback;
    }

    public String getTrigger() {
        return trigger;
    }

    public void setTrigger(String trigger) {
        this.trigger = trigger;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Event{" +
                "source=" + source +
                ", target=" + target +
                ", callback=" + callback +
                ", trigger='" + trigger + '\'' +
                ", time=" + time +
                '}';
    }
}
