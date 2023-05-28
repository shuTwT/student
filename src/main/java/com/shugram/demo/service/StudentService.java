package com.shugram.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shugram.demo.entity.StudentEntity;

import java.util.List;
import java.util.Map;


public interface StudentService extends IService<StudentEntity> {
    String resolveStudentList(List<Map<String,String>> studentList);
}
