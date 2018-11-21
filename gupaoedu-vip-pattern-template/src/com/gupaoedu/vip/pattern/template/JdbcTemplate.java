package com.gupaoedu.vip.pattern.template;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
    private DataSource dataSource;
    public JdbcTemplate(DataSource dataSource) {
        this.dataSource=dataSource;
    }
    private Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
    public List<?> executeQuerey(String sql,RowMapper<?> rowMapper,Object[] params) throws Exception {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            Object param = params[i];
            preparedStatement.setObject(i,param);
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<Object> objects = new ArrayList<>();
        int rowNum=1;
        while (resultSet.next()) {
            Object o = rowMapper.mapRow(resultSet,rowNum++);
            objects.add(o);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();
        return objects;
    }
}
