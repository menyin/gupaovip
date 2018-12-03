package gupaoedu.vip.mybatis.plugns;

import gupaoedu.vip.mybatis.base.mapper.StudentMapper;
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
            //测试插件执行顺序，分别经过了2个插件 gupaoedu.vip.mybatis.plugns.ExamplePlugin1/ExamplePlugin2
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            Student student = mapper.getStudentById(1);
            System.out.println(student);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
    }
}
