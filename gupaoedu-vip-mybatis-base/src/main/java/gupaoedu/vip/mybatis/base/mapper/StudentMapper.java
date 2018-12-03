package gupaoedu.vip.mybatis.base.mapper;

import gupaoedu.vip.mybatis.base.pojo.Student;

public interface StudentMapper {
    Student getStudentById(int id);
    int insertStudent(Student student);
}
