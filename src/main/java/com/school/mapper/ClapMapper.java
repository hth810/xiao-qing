package com.school.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClapMapper {
    @Insert("insert into clap(user_name,create_time) values(#{username},now())" )
    void participate(String username);


    @Select("select count(*) from clap")
    long getTotalParticipants();
}
