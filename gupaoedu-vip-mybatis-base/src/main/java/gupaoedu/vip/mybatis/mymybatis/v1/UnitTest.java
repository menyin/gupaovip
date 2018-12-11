package gupaoedu.vip.mybatis.mymybatis.v1;

import gupaoedu.vip.mybatis.mymybatis.v1.entity.Person;
import gupaoedu.vip.mybatis.mymybatis.v1.impl.DefaultSqlsession;
import gupaoedu.vip.mybatis.mymybatis.v1.impl.SimpleExcutor;

public class UnitTest {
    /**
     * v1版本只理清Excutor、Sqlsession、Configuration、MapperProxy的关系
     * @param args
     */
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        Excutor excutor = new SimpleExcutor(configuration);
        Sqlsession sqlsession = new DefaultSqlsession(excutor,configuration);
        PersonMapper mapper = sqlsession.getMapper(PersonMapper.class);
        Person person = mapper.getPersonById("1");
        System.out.println(person.getName());
    }
}
