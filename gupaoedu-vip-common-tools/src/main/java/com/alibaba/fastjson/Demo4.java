package com.alibaba.fastjson;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * fastjson的基本使用
 * 详见 https://www.cnblogs.com/nananana/p/8921800.html
     FastJson对于json格式字符串的解析主要用到了下面三个类：
     1.JSON：fastJson的解析器，用于JSON格式字符串与JSON对象及javaBean之间的转换
     2.JSONObject：fastJson提供的json对象
     3.JSONArray：fastJson提供json数组对象
 注意：如果javabean属性为null则转为json时不会显示出来对应的属性
 */
public class Demo4 {
    public static void main(String[] args) {
        //map不为null时
        /*HashMap<String, String> map = new HashMap<>();
        map.put("name", "jack");
        map.put("age", "12");
        System.out.println(map);
        System.out.println(JSON.toJSONString(map));*/

        //map为null时
        /*HashMap<String, String> map =null;
        System.out.println(JSON.toJSONString(map));*/

        //map为复杂键值时
        /*HashMap<String, Student> map = new HashMap<>();
        map.put("jack", new Student("jack", 18));
        map.put("rose", new Student("rose", 13));
        System.out.println(JSON.toJSONString(map));*/

        //map为泛型时
        /*HashMap<String, Student> map = new HashMap<>();
        map.put("jack", new Student("jack", 18));
        map.put("rose", new Student("rose", 13));
        System.out.println(toJson(map));*/

    }

    public static <T,R> String toJson(Map<T,R> map) {
        if (map==null) {
            return "{}";
        }
        return JSON.toJSONString(map);
    }
    public static class Student{
        private String studentName;
        private int studentAge;

        public Student(String studentName, int studentAge) {
            this.studentName = studentName;
            this.studentAge = studentAge;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public int getStudentAge() {
            return studentAge;
        }

        public void setStudentAge(int studentAge) {
            this.studentAge = studentAge;
        }
    }

    //json字符串-简单对象型
    private static final String  JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";
    //json字符串-数组类型
    private static final String  JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";
    //复杂格式json字符串
    private static final String  COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";
}

