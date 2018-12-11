package gupaoedu.vip.mybatis.errorcontext;

import gupaoedu.vip.mybatis.base.mapper.StudentMapper;
import gupaoedu.vip.mybatis.base.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class UnitTest {
    public static void main(String[] args) {
        try {
            //测试测试ErrorContext工作原理
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            sqlSession.selectList("", new Object(), new RowBounds(1,10));//构造错误

        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
    }
}
