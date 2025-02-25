package com.school.mapper;

import com.school.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    // 根据用户名查询用户
    @Select("select * from user where username = #{username}")
    User findByUserName(String username);

    @Select("select * from user where id = #{id}")
    User findById(Integer id);
    // 添加用户
    @Insert("insert into user(username, password,create_time,update_time)" +
            " values (#{username},#{password},now(),now())")
    void add(String username, String password);
    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    void update(User user);

    @Update("update user set participated=#{participated} where id=#{id}")
    void participate(User user);
}
