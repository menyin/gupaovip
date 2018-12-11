package gupaoedu.vip.mybatis.mymybatis.v2.session;


import gupaoedu.vip.mybatis.mymybatis.v2.bingding.MapperProxy;
import gupaoedu.vip.mybatis.mymybatis.v2.excutor.Excutor;
import gupaoedu.vip.mybatis.mymybatis.v2.mapperstatement.MappedStatement;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DefaultSqlsession implements Sqlsession {
    private Excutor excutor;
    private Configuration configuration;
    public DefaultSqlsession(Excutor excutor, Configuration configuration) {
        List<InvocationHandler> plugns = configuration.getPlugns();
        this.excutor = Configuration.getWrapperExcutor(excutor);//插件包装
        this.configuration = configuration;
    }

    public <E> E selectOne(String statement) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        return null;
    }

    /**
     *
     * @param statement 执行方法的全名 如 gupaoedu.vip.mybatis.mymybatis.v2.PersonMapper.getPersonById
     * @param parameter sql参数
     * @param <E>
     * @return
     * @throws SQLException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     */
    public <E> List<E> selectList(String statement, Object parameter) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        MappedStatement mappedStatement= new MappedStatement(statement);
        return excutor.<E>query(mappedStatement, parameter);
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        return null;
    }
    public <T> T getMapper(Class<T> mapperClass) {
        return MapperProxy.<T>newInstance (mapperClass,this);
    }
}
