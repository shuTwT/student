package com.shugram.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shugram.demo.entity.CourseEntity;
import com.shugram.demo.pojo.Course;

import java.util.List;

public interface CourseService extends IService<CourseEntity> {
    List<Course> findCourseAll();
    Long findIdByName(String courseName);
}
