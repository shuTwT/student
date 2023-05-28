package com.shugram.demo.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shugram.demo.entity.StudentEntity;
import com.shugram.demo.mapper.StudentMapper;
import com.shugram.demo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentEntity> implements StudentService {
    public void resolveStudentList(List<Map<String,String>> studentList){
    System.out.println(studentList);
    }
}
