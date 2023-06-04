package com.shugram.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shugram.demo.entity.Pager;
import com.shugram.demo.entity.StudentEntity;
import com.shugram.demo.pojo.Student;
import com.shugram.demo.utils.Response;

import java.util.List;
import java.util.Map;


public interface StudentService extends IService<StudentEntity> {
    Response<String> resolveStudentList(List<Map<String,String>> studentList);
    Pager<Student> getStudentListByPage(int page, int size);
    Long findIdByName(String stuName);
}
