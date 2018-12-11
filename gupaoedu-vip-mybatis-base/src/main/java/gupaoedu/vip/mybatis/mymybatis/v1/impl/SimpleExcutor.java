package gupaoedu.vip.mybatis.mymybatis.v1.impl;

import gupaoedu.vip.mybatis.mymybatis.v1.Configuration;
import gupaoedu.vip.mybatis.mymybatis.v1.Excutor;
import gupaoedu.vip.mybatis.mymybatis.v1.Sqlsession;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleExcutor implements Excutor {
    private Configuration configuration;

    public SimpleExcutor(Configuration configuration) {
        this.configuration = configuration;
    }



    public <E> List<E> query(String statement , Object parameter, Sqlsession sqlsession) throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        Connection connection = sqlsession.getConnection();
        statement=statement.replace("#{id}", "?");
        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        preparedStatement.setObject(1,parameter);

        ResultSet resultSet = preparedStatement.executeQuery();
        ArrayList<E> list = new ArrayList<E>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            int age = resultSet.getInt(3);
//            Person person = new Person(id, name, age);

            try {
                Class<?> clazz= Class.forName("gupaoedu.vip.mybatis.mymybatis.v1.entity.Person");//configuration中获取resultType,此处省略
                E bean =(E) clazz.newInstance();
                Field[] fs = clazz.getDeclaredFields();
                for (Field field : fs) {
                    // 要设置属性可达，不然会抛出IllegalAccessException异常
                    field.setAccessible(true);
                    // 设置属性值，set(Object obj, Object value)
                    if(field.getName().equals("id")){
                        field.set(bean, id);
                    }
                    if(field.getName().equals("name")){
                        field.set(bean, name);
                    }
                    if(field.getName().equals("age")){
                        field.set(bean, age);
                    }
                }
                list.add((E) bean);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
        return list;
    }


}
