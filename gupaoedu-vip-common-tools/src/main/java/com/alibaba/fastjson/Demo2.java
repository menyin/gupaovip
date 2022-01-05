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
public class Demo2 {
    public static void main(String[] args) {
        

        //字符串转JavaBean对象
        /*Student student = JSONObject.parseObject(JSON_OBJ_STR, Student.class);
        System.out.println(student.getStudentName());
        System.out.println(student.getStudentAge());*/

        //JavaBean对象转字符串 （注意getNick方法并无对一个的nick属性）
        /*Student jack = new Student("jack", 18);
        System.out.println(JSONObject.toJSONString(jack));
        System.out.println(JSON.toJSONString(jack));*/


        //字符串转集合
        /*List<Student> students = JSONArray.parseArray(JSON_ARRAY_STR, Student.class);
        students.stream().forEach(student -> System.out.println(student.studentName + ":" + student.studentAge));*/

        //集合转字符串
        /*List<Student> students = Arrays.asList(new Student("jack", 18), new Student("rose", 12));
        System.out.println(JSON.toJSONString(students));
        System.out.println(JSONObject.toJSONString(students));*/

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

        public String getNick(){
            return "小狗子";}
    }

    //json字符串-简单对象型
    private static final String  JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";
    //json字符串-数组类型
    private static final String  JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";
    //复杂格式json字符串
    private static final String  COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";
}

