package com.gupaoedu.vip.pattern.singleton.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RegisterMap {
    private static Map<String, Object> register = new ConcurrentHashMap<>();//ConcurrentHashMap是线程安全的
    public static RegisterMap getInstance() throws IllegalAccessException, InstantiationException {
        if (register.get(RegisterMap.class.getName()) == null) {
            register.put(RegisterMap.class.getName(), RegisterMap.class.newInstance());
        }
        return (RegisterMap) register.get(RegisterMap.class.getName());
    }
}
