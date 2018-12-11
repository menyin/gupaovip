package gupaoedu.vip.mybatis.mymybatis.v1.impl;

import gupaoedu.vip.mybatis.base.pojo.Person;
import gupaoedu.vip.mybatis.mymybatis.v1.Configuration;
import gupaoedu.vip.mybatis.mymybatis.v1.Excutor;
import gupaoedu.vip.mybatis.mymybatis.v1.MapperProxy;
import gupaoedu.vip.mybatis.mymybatis.v1.Sqlsession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DefaultSqlsession implements Sqlsession {
    private Excutor excutor;
    private Configuration configuration;

    public DefaultSqlsession(Excutor excutor, Configuration configuration) {
        this.excutor = excutor;
        this.configuration = configuration;
    }

    public <T> T getMapper(Class<T> mapperClass) {

        return MapperProxy.<T>newInstance(mapperClass,this);
    }

    public <E> E selectOne(String statement) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        return (E)selectList(statement,null).get(0);

    }

    public <E> List<E> selectList(String statement, Object parameter) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        return this.excutor.<E>query(statement, parameter, this);
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {

        //1.驱动注册程序
        Class.forName(configuration.getDatabaseDriver());
        //2.获取连接对象
        return DriverManager.getConnection(configuration.getDatabaseUrl(),configuration.getDatabaseUserName(),configuration.getDatabaseUserPwd());
    }



    public Excutor getExcutor() {
        return excutor;
    }

    public void setExcutor(Excutor excutor) {
        this.excutor = excutor;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
