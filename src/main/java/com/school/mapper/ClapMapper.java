package com.school.mapper;

import com.school.pojo.Clap;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClapMapper {
    @Insert("insert into clap(user_name,create_time) values(#{username},now())" )
    void participate(String username);


    @Select("select count(*) from clap")
    long getTotalParticipants();

    @Select("select * from clap where user_name = #{username}")
    Clap findByUserName(String username);
}
