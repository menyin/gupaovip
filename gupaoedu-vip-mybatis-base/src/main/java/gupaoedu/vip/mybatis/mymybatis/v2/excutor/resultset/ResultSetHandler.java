package gupaoedu.vip.mybatis.mymybatis.v2.excutor.resultset;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetHandler {

    public <E> E process(ResultSet resultSet) {
        try {
        String id = resultSet.getString(1);
        String name = resultSet.getString(2);
        int age = resultSet.getInt(3);

            Class<?> clazz= Class.forName("gupaoedu.vip.mybatis.mymybatis.v2.Person");//configuration中获取resultType,此处省略
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
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

}
