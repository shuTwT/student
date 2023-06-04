package com.shugram.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shugram.demo.entity.CourseEntity;
import com.shugram.demo.pojo.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<CourseEntity> {
    @Select("SELECT course_id,course_name,teacher_name,course_hour,course_score FROM course,teacher where course_tid=teacher_id")
    List<Course> findCourseAll();
   }
