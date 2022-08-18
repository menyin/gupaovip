package com.gupaoedu.vip.mybatisplustest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gupaoedu.vip.mybatisplustest.mappers.UserMapper;
import com.gupaoedu.vip.mybatisplustest.modes.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisplusTestApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
/*
        List<User> userList = userMapper.selectList(new QueryWrapper<User>().lambda().eq(User::getName,"Jack"));
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
*/

        /*User user= userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getName,"Jack"));
        System.out.println(user);*/

        User user = new User();
        user.setName("艾弗森");
        user.setAge(18);
        user.setEmail("139@qq.com");
        userMapper.insert(user);
        System.out.println("我的id是："+user.getId());

    }
    @Test
    void contextLoads() {
    }

}
