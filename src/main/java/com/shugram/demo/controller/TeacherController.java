package com.shugram.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shugram.demo.entity.TeacherEntity;
import com.shugram.demo.service.TeacherService;
import com.shugram.demo.utils.PassToken;
import com.shugram.demo.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="教师接口")
@RequestMapping("teacher")
@RestController
public class TeacherController {
    private final TeacherService teacherService;
    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    @PassToken
    @Operation(summary="教师列表")
    @RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
    public Response<List<TeacherEntity>> getList() {
        List<TeacherEntity> teacherList= teacherService.list();
        return Response.success(teacherList);
    }
    @Operation(summary="添加教师")
    @PostMapping("/add")
    public Response<String> addStudent(@RequestBody TeacherEntity teacher){
        QueryWrapper<TeacherEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("SELECT 1")
                .eq("stu_id",teacher.getTeacherId())
                .last("LIMIT 1");
        int count= teacherService.count(queryWrapper);
        if (count!=0){
            return Response.fail(400,"已存在数据");
        }else{
            teacherService.saveOrUpdate(teacher);
            return Response.success("");
        }
    }
    @Operation(summary="更新教师信息")
    @PostMapping("/update")
    public Response<String> updateStudent(@RequestBody TeacherEntity teacher){
        QueryWrapper<TeacherEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("SELECT 1")
                .eq("stu_id",teacher.getTeacherId())
                .last("LIMIT 1");
        int count= teacherService.count(queryWrapper);
        if (count==0){
            return Response.fail(400,"不存在数据");
        }else{
            teacherService.saveOrUpdate(teacher);
            return Response.success("");
        }
    }
}
