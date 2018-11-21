package com.gupaoedu.vip.pattern.template;

import java.sql.ResultSet;

public interface RowMapper<T> {
    public T mapRow(ResultSet resultSet,int rowNum) throws Exception;
}
