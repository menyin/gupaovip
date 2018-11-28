package gupaoedu.vip.mybatis.typehandler;

import gupaoedu.vip.mybatis.base.mapper.PersonMapper;
import gupaoedu.vip.mybatis.base.mapper.StudentMapper;
import gupaoedu.vip.mybatis.base.pojo.Person;
import gupaoedu.vip.mybatis.base.pojo.Student;
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
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            Student student = mapper.getStudentById(1);
            System.out.println(student);
            Student zhizunbao = new Student(23, "至尊宝", "水帘洞");
            int result = mapper.insertStudent(zhizunbao);
            sqlSession.commit();
            sqlSession.close();
            System.out.println(result);


        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
    }
}
