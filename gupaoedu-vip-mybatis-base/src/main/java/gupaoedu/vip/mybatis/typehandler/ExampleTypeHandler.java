package gupaoedu.vip.mybatis.typehandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@MappedJdbcTypes(JdbcType.VARCHAR)
public class ExampleTypeHandler extends BaseTypeHandler<String> {
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, String s, JdbcType jdbcType) throws SQLException {
//        JdbcType.BIGINT
        preparedStatement.setString(i, s + "-TypeHandler-Write");
    }

    public String getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.getString(s) + "-TypeHandler-Return-getNullableResult-name";
    }

    public String getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getString(i) + "-TypeHandler-Return-getNullableResult-index";
    }

    public String getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getString(i)+ "-TypeHandler-Return-getNullableResult";
    }
}
