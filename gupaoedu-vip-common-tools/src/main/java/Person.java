import java.util.Date;

/**
 * @Description: TODO
 * @author: geduo
 * @date: 2023年03月03日 13:50
 */

public class Person {
    private Integer age;
    private String name;
    private Date birthDate;
    private String add;
    private String school;
    private String decription;
    private int i=1;
    public Person(Integer age, String name, Date birthDate, String add, String school) {
        this.age = age;
        this.name = name;
        this.birthDate = birthDate;
        this.add = add;
        this.school = school;
        this.decription = "马化腾 ，男，1971年10月29日生于广东省汕头市潮南区。腾讯公司主要创办人之一，现担任腾讯公司控股董事会**兼首*执行官；全国青联副**他曾在深圳大学主修计算机及应用，于1993年取得深大理科学士学位。在创办腾讯之前，马化腾曾在中国电信服务和产品供应商深圳润迅通讯发展有限公司主管互联网传呼系统的研究开发工作，在电信及互联网行业拥有10多年经验。1998年和好友张志东注册成立深圳市腾讯计算机系统有限公司。2009年，腾讯入选《财富》“全球最受尊敬50家公司”。在2014年3000中国家族财富榜中马化腾以财富1007亿元荣登榜首，相比于2013年，财富增长了540亿元。";
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }
}
