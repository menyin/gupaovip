package com.gupaoedu.vip.distributed.io.prestudy.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapDemo {
    public static void main(String[] args) {
        //Map都是键不可重复的双列集合，如果put相同的键则会覆盖原有的值
        Map<String, String> map = new HashMap<String, String>();
        Map<String, String> map2 = new HashMap<String, String>();
        map.put("a", "小红");
        map.put("b", "小黑");
        map.put("c", "小黄");

        map2.put("b", "小黑");
        map2.put("c", "小黄");
        map2.put("d", "小绿");
        map.putAll(map2);//取并集
        System.out.println(map);

        System.out.println(map.get("a"));

        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key+":"+value);
        }

        Collection<String> values = map.values();
        for (String value : values) {
            System.out.println(value);//value是可能重复的
        }

        map.put("a", "小王");
        System.out.println(map);


    }
}
