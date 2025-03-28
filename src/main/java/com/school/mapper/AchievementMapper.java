package com.school.mapper;

import com.school.pojo.Achievement;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AchievementMapper {
    @Insert("insert into achievement(username,achievement_name,create_time) values(#{username},#{achievementName},now())")
    void saveAchievement(Achievement achievement);

    @Select("select * from achievement where username = #{username}")
    List<Achievement> getAchievementsByUsername(@Param("username") String username);
}
