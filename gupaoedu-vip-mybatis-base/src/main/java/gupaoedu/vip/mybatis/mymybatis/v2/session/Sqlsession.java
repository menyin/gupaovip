package gupaoedu.vip.mybatis.mymybatis.v2.session;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface Sqlsession {
    <E> E selectOne(String statement) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException;

    <E> List<E> selectList(String statement, Object parameter) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException;
    Connection getConnection() throws ClassNotFoundException, SQLException;

    <T> T getMapper(Class<T> mapperClass);

}
