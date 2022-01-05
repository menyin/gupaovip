package com.java.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;

public class Demo1 {
    public static void main(String[] args) {
        //java开源的Jackson
        Student jack = new Student("jack", 18);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(jack);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static class Student {
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

        public String getNick() {
            return "小狗子";
        }
    }
}
