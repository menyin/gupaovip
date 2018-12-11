package gupaoedu.vip.mybatis.mymybatis.v1;

import gupaoedu.vip.mybatis.mymybatis.v1.impl.DefaultSqlsession;

import java.sql.SQLException;
import java.util.List;

public interface Excutor {
    <E> List<E> query(String statement, Object parameter, Sqlsession Sqlsession) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException;
}
