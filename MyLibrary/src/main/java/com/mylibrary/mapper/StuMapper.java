package com.mylibrary.mapper;

import com.mylibrary.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface StuMapper {

    @Select("select * from student where stu_id=#{id} and name=#{name}")
    @Result(column = "stu_id",property = "id")
    Student StuVerify(@Param("id")int id,@Param("name")String name);
    Student StuVerify(Student student);


    @Select("select * from student")
    @Results({
            @Result(column = "stu_id", property = "id"),
            @Result(column = "name", property = "name")
    })
    List<Student> getAllStudent();

    @Insert("insert into student values(#{id},#{name})")
    int addStudent(@Param("id") int id,@Param("name")String name);

    @Select("select * from student where stu_id=#{sid}")
    @Result(column = "stu_id",property = "id")
    Student getStuById(@Param("sid") int id);

    @Select("select count(*) from student")
    int getStuNum();

    @Delete("delete from student where stu_id=#{sid}")
    void deleteStuBySid(@Param("sid") int sid);
}
