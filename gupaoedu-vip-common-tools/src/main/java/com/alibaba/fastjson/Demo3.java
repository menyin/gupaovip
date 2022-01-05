package com.alibaba.fastjson;

import java.util.Arrays;
import java.util.List;

/**
 * fastjson的基本使用
 * 详见 https://www.cnblogs.com/nananana/p/8921800.html
     FastJson对于json格式字符串的解析主要用到了下面三个类：
     1.JSON：fastJson的解析器，用于JSON格式字符串与JSON对象及javaBean之间的转换
     2.JSONObject：fastJson提供的json对象
     3.JSONArray：fastJson提供json数组对象
 注意：如果javabean属性为null则转为json时不会显示出来对应的属性
 */
public class Demo3 {
    public static void main(String[] args) {
        //集合转JSONArray
        /*List<Demo2.Student> students = Arrays.asList(new Student("jack", 18), new Student("rose", 12));
        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(students);
        System.out.println(jsonArray);*/

        //JSONArray转集合
        List<Object> students = Arrays.asList(new Student("jack", 18), new Student("rose", 12));
        JSONArray jsonArray = new JSONArray(students);
        List<Student> students1 = JSONObject.parseArray(jsonArray.toJSONString(), Student.class);
        List<Student> students2 = JSON.parseArray(jsonArray.toJSONString(), Student.class);
        System.out.println(students1);//主义这两个list的内存地址是不一样的
        System.out.println(students2);


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

