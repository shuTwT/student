package com.shugram.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shugram.demo.entity.StudentEntity;
import com.shugram.demo.service.StudentService;
import com.shugram.demo.utils.PassToken;
import com.shugram.demo.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="学生接口")
@RequestMapping("student")
@RestController
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PassToken
    @Operation(summary="学生列表")
    @RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
    public Response<List<StudentEntity>> getList() {
        List<StudentEntity> stuList= studentService.list();
        return Response.success(stuList);
    }

    @Operation(summary="添加学生")
    @Parameters({
            @Parameter(name = "stuId",description = "学生id",in = ParameterIn.PATH),
            @Parameter(name = "stuName",description = "学生姓名",required = true,in = ParameterIn.HEADER),
            @Parameter(name = "stuAge",description = "学生年龄",required = true,in=ParameterIn.QUERY)
    })
    @PostMapping("/add")
    public Response<String> addStudent(@RequestBody StudentEntity student){
        QueryWrapper<StudentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("SELECT 1")
                .eq("stu_id",student.getStuId())
                .last("LIMIT 1");
        int count= studentService.count(queryWrapper);
        if (count!=0){
            return Response.fail(400,"已存在数据");
        }else{
            studentService.saveOrUpdate(student);
            return Response.success("");
        }
    }
    @Operation(summary="更新学生信息")
    @PostMapping("/update")
    public Response<String> updateStudent(@RequestBody StudentEntity student){
        QueryWrapper<StudentEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("SELECT 1")
                .eq("stu_id",student.getStuId())
                .last("LIMIT 1");
        int count= studentService.count(queryWrapper);
        if (count==0){
            return Response.fail(400,"不存在数据");
        }else{
            studentService.saveOrUpdate(student);
            return Response.success("");
        }
    }
    @Operation(summary="更新学生信息")
    @PostMapping("/delete")
    public void deleteStudent(){

    }
    @Operation(summary="重置学生密码")
    @PostMapping("/resetPassword")
    public void resetPassword(){

    }
    @ExceptionHandler(Exception.class)
    public Response<Object> handleException(Exception e) {
        return Response.fail(500, e.getMessage());
    }

}
