package com.mylibrary.mapper;


import com.mylibrary.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    //使用mybatis注解编程，在数据库中查找用户和对应的密码
    @Select("select * from user where username=#{username} and password=#{password}")
    public User ValidUser(@Param("username") String username, @Param("password") String password);
    public User ValidUser(User user);
}
