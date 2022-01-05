package com.alibaba.fastjson;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * fastjson的基本使用
 * 详见 https://www.cnblogs.com/nananana/p/8921800.html
     FastJson对于json格式字符串的解析主要用到了下面三个类：
     1.JSON：fastJson的解析器，用于JSON格式字符串与JSON对象及javaBean之间的转换
     2.JSONObject：fastJson提供的json对象
     3.JSONArray：fastJson提供json数组对象
   注意：如果javabean属性为null则转为json时不会显示出来对应的属性
 */
public class Demo1 {
    public static void main(String[] args) {


        //字符串转JSONObject对象
        /*JSONObject jsonObject = JSONObject.parseObject(JSON_OBJ_STR);
        System.out.println(jsonObject.get("studentName"));
        System.out.println(jsonObject.get("studentAge"));*/

        //JSONObject对象转字符串
        /*JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "jack");
        jsonObject.put("age", 18);
        System.out.println(jsonObject.toJSONString());*/

        //字符串转JSONArray对象
        /*JSONArray jsonArray = JSONArray.parseArray(JSON_ARRAY_STR);
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject)o;
            System.out.println(jsonObject);
        }*/

        //JSONArray对象转字符串
        /*JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name", "jack");
        jsonObject1.put("age", 18);
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("name", "rose");
        jsonObject2.put("age", 12);
        jsonArray.add(jsonObject1);
        jsonArray.add(jsonObject2);
        System.out.println(jsonArray.toJSONString());*/

        HashMap<Integer, String> map = new HashMap<>();
        Integer userId=123;
        map.put(userId, "毛主席万岁");
        String s = map.computeIfAbsent(userId, t -> "社会主义好");
        System.out.println(s);


    }

    //json字符串-简单对象型
    private static final String  JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";
    //json字符串-数组类型
    private static final String  JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";
    //复杂格式json字符串
    private static final String  COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";
}

