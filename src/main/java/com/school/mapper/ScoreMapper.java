package com.school.mapper;

import com.school.pojo.Score;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScoreMapper {
    @Insert("insert into score(username,score,create_time) values(#{username},#{score},now())")
    void saveScore(Score score);

    @Select("select * from score order by score desc limit #{limit}")
    List<Score> getTopScores(@Param("limit") int limit);

    @Select("select * from score where username = #{username} order by score desc")
    List<Score> getScoresByUsername(@Param("username") String username);
}
