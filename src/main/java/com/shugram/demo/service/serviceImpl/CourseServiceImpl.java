package com.shugram.demo.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shugram.demo.entity.CourseEntity;
import com.shugram.demo.entity.MajorEntity;
import com.shugram.demo.mapper.CourseMapper;
import com.shugram.demo.pojo.Course;
import com.shugram.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, CourseEntity> implements CourseService {
    private final CourseMapper courseMapper;

    @Autowired
    public CourseServiceImpl(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }
    public List<Course> findCourseAll(){
        return courseMapper.findCourseAll();
    }
    public Long findIdByName(String courseName){
        CourseEntity one= this.getOne(new QueryWrapper<CourseEntity>()
                .eq("course_name",courseName)
                .last("limit 1")
        );
        return one.getCourseId();
    }
}
