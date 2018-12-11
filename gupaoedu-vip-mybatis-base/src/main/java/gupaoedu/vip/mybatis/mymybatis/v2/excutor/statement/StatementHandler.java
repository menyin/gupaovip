package gupaoedu.vip.mybatis.mymybatis.v2.excutor.statement;

import gupaoedu.vip.mybatis.mymybatis.v2.excutor.resultset.ResultSetHandler;
import gupaoedu.vip.mybatis.mymybatis.v2.mapperstatement.MappedStatement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatementHandler {

    public <E> List<E> query(MappedStatement ms, Object parameter, ResultSetHandler resultSetHandler) throws ClassNotFoundException, SQLException {
        Class<?> mysqldriver = Class.forName( "com.mysql.jdbc.Driver");//理论上是从Configuration获得，此处从简
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3309/test1?useUnicode=true&characterEncoding=utf-8",
                "root", "root");//理论上是从Configuration获得参数，此处从简
        PreparedStatement preparedStatement = connection.prepareStatement(ms.getSql().replace("#{id}","?"));
        Object[] param= (Object[]) parameter;
        for (int i=0;i<param.length;i++) {
            preparedStatement.setObject(i+1, param[i]);
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        List<E> es = new ArrayList<E>();
        while (resultSet.next()){
            E processResult = resultSetHandler.<E>process(resultSet);
            es.add(processResult);
        }

        return es;
    }
}
