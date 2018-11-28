package gupaoedu.vip.mybatis.base.mapper;

import gupaoedu.vip.mybatis.base.pojo.Person;
import org.apache.ibatis.annotations.Select;

public interface PersonMapper {
    Person getPersonById(int id);
    @Select("SELECT * FROM person WHERE name = #{name}")
    Person getPersonByName(String name);
}
