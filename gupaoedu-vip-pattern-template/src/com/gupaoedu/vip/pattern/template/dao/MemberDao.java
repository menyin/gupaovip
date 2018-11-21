package com.gupaoedu.vip.pattern.template.dao;

import com.gupaoedu.vip.pattern.template.JdbcTemplate;
import com.gupaoedu.vip.pattern.template.RowMapper;
import com.gupaoedu.vip.pattern.template.pojo.Member;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

public class MemberDao {
    private JdbcTemplate jdbcTemplate =new JdbcTemplate(null);
    public List<Member> equeryAll() throws Exception {
        String sql = "select * from tb_member";
        jdbcTemplate.executeQuerey(sql, new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet resultSet, int rowNum) throws Exception {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                return new Member(id, name, age);
            }
        }, null);
        return null;
    }
}
