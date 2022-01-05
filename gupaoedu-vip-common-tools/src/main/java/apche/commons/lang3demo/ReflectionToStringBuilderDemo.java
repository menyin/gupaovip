package apche.commons.lang3demo;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Date;

public class ReflectionToStringBuilderDemo {
    public static void main(String[] args) {
        Person person = new Person("007", 18, new Date());
        System.out.println(ReflectionToStringBuilder.toString(person));
        System.out.println(person);
    }

    public static class Person {
        private String id;
        private int age;
        private Date birth;

        public Person(String id, int age, Date birth) {
            this.id = id;
            this.age = age;
            this.birth = birth;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public Date getBirth() {
            return birth;
        }

        public void setBirth(Date birth) {
            this.birth = birth;
        }
    }
}
