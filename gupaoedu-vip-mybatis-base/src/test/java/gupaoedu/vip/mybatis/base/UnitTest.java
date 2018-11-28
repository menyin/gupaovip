package gupaoedu.vip.mybatis.base;

import gupaoedu.vip.mybatis.base.mapper.PersonMapper;
import gupaoedu.vip.mybatis.base.pojo.Person;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class UnitTest {
    public static void main(String[] args) {
        try {
//            String classpath = UnitTest.class.getResource("/").getPath();
//            String resource = classpath+ "gupaoedu/vip/maybatis/base/mapper/PersonMapper.xml";

            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
            //getPersonById的sql是xml配置
            Person personXml = mapper.getPersonById(1);
            //getPersonByNamed的sql是注解配置
            Person personAnnotations = mapper.getPersonByName("lisi");
            System.out.println(personXml.getName());
            System.out.println(personAnnotations.getName());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
