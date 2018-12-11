package gupaoedu.vip.mybatis.mymybatis.v2.mapperstatement;

import gupaoedu.vip.mybatis.mymybatis.v2.excutor.resultset.ResultSetHandler;
import gupaoedu.vip.mybatis.mymybatis.v2.session.Configuration;

import java.util.List;

public class MappedStatement {
    private String sql;
    public MappedStatement(String statement) {
        this.sql=Configuration.getMapperSql(statement);
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
