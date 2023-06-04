package com.shugram.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shugram.demo.entity.ScoreEntity;
import com.shugram.demo.pojo.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ScoreMapper extends BaseMapper<ScoreEntity> {
    @Select("SELECT score_id,course_name,teacher_name,stu_name,score_num FROM score,course,student,teacher where score_cid=course_id and score_sid=stu_id and course_tid=teacher_id;")
    List<Score> findScoreAll();
    @Update("UPDATE score SET score_num=CAST(score_num AS INT)+10 where score_id=#{score_id}")
    void inCreaseScore(@Param("score_id") Long scoreId);
    @Update("UPDATE score SET score_num=CAST(score_num AS INT)-10 where score_id=#{score_id}")
    void reduceScore(@Param("score_id") Long scoreId);
}
