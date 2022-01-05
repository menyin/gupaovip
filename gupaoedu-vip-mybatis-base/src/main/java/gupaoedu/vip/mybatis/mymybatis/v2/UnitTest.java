package gupaoedu.vip.mybatis.mymybatis.v2;

import gupaoedu.vip.mybatis.mymybatis.v2.excutor.CachingExcutor;
import gupaoedu.vip.mybatis.mymybatis.v2.excutor.SimpleExcutor;
import gupaoedu.vip.mybatis.mymybatis.v2.session.Configuration;
import gupaoedu.vip.mybatis.mymybatis.v2.session.DefaultSqlsession;
import gupaoedu.vip.mybatis.mymybatis.v2.session.Sqlsession;

import java.util.List;

public class UnitTest {
    /**
     * 相对于v1版本，v2版本做了以下改进
     * 规范了package
     * 增加plugin功能
     * 增加CachingExcutor一级缓存包装
     * 增加了StatementHandler、ResutsetHandler分工处理
     */
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        Sqlsession sqlsession = new DefaultSqlsession(new CachingExcutor(new SimpleExcutor(configuration)),configuration);
        PersonMapper mapper = sqlsession.getMapper(PersonMapper.class);
        Person person = mapper.getPersonById("1");
        System.out.println(person.getName());
       Boolean iss=false;
        boolean is=iss;


    }
}
