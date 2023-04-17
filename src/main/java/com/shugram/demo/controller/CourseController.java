package com.shugram.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shugram.demo.entity.CourseEntity;
import com.shugram.demo.pojo.Course;
import com.shugram.demo.service.CourseService;
import com.shugram.demo.utils.PassToken;
import com.shugram.demo.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="课程接口")
@RequestMapping("course")
@RestController
public class CourseController {
    private final CourseService courseService;
    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @PassToken
    @Operation(summary="课程列表")
    @RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
    public Response<List<Course>> getList() {
        List<Course> courseList= courseService.findCourseAll();
        return Response.success(courseList);
    }



    @Operation(summary="添加课程")
    @PostMapping("/add")
    public Response<String> addStudent(@RequestBody CourseEntity course){
        QueryWrapper<CourseEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("SELECT 1")
                .eq("stu_id",course.getCourseId())
                .last("LIMIT 1");
        int count= courseService.count(queryWrapper);
        if (count!=0){
            return Response.fail(400,"已存在数据");
        }else{
            courseService.saveOrUpdate(course);
            return Response.success("");
        }
    }
    @Operation(summary="更新课程信息")
    @PostMapping("/update")
    public Response<String> updateStudent(@RequestBody CourseEntity course){
        QueryWrapper<CourseEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("SELECT 1")
                .eq("stu_id",course.getCourseId())
                .last("LIMIT 1");
        int count= courseService.count(queryWrapper);
        if (count==0){
            return Response.fail(400,"不存在数据");
        }else{
            courseService.saveOrUpdate(course);
            return Response.success("");
        }
    }
}
