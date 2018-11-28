package com.gupaoedu.vip.pattern.observe.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EventListener {
    private Map<Enum, Event> eventMap = new HashMap<>();

    public void addEventListener(Enum eventType, Object target, Method callback) {
        eventMap.put(eventType, new Event(this, target, callback, eventType.toString(), System.currentTimeMillis()));
    }

    protected void triggerEvent(Enum eventType) throws InvocationTargetException, IllegalAccessException {
        if(eventMap.containsKey(eventType)){
            Event event = eventMap.get(eventType);
            Object target = event.getTarget();
            Method callback = event.getCallback();
            callback.invoke(target, event);
        }
    }
}
