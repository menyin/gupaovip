package gupaoedu.vip.mybatis.mymybatis.v2.excutor;

import gupaoedu.vip.mybatis.mymybatis.v2.mapperstatement.MappedStatement;

import java.sql.SQLException;
import java.util.List;

public interface Excutor {
//    <E> List<E> query(String statement, Object parameter, Sqlsession Sqlsession) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException;
    <E> List<E> query(MappedStatement ms, Object parameter) throws SQLException, ClassNotFoundException;
}
