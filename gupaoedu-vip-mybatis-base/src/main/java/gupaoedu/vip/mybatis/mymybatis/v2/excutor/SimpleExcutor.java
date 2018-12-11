package gupaoedu.vip.mybatis.mymybatis.v2.excutor;

import gupaoedu.vip.mybatis.mymybatis.v2.excutor.resultset.ResultSetHandler;
import gupaoedu.vip.mybatis.mymybatis.v2.excutor.statement.StatementHandler;
import gupaoedu.vip.mybatis.mymybatis.v2.mapperstatement.MappedStatement;
import gupaoedu.vip.mybatis.mymybatis.v2.session.Configuration;

import java.sql.SQLException;
import java.util.List;

public class SimpleExcutor implements Excutor {
    private Configuration configuration;
    public SimpleExcutor(Configuration configuration) {
        this.configuration = configuration;
    }

    public <E> List<E> query(MappedStatement mappedStatement, Object parameter) throws SQLException, ClassNotFoundException {
        ResultSetHandler resultSetHandler = new ResultSetHandler();
        StatementHandler statementHandler = new StatementHandler();
        return statementHandler.query(mappedStatement,parameter,resultSetHandler);
    }
}
