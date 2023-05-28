package com.shugram.demo.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shugram.demo.entity.StudentEntity;
import com.shugram.demo.mapper.StudentMapper;
import com.shugram.demo.service.MajorService;
import com.shugram.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentEntity> implements StudentService {

    private final MajorService majorService;
    @Autowired
    public StudentServiceImpl(MajorService majorService){
        this.majorService=majorService;
    }

    public String resolveStudentList(List<Map<String,String>> studentList){
    System.out.println(studentList);
    studentList.forEach(item->{
        // 通过学号判断是否存在该学生
        int count=this.count(new LambdaQueryWrapper<StudentEntity>()
                .exists("select stu_id from student where stu_id = '"+item.get("学生学号")+"'")
        );
        if (count>0){
            // 该学生存在
            Long majorId= majorService.findIdByName(item.get("专业"));
            // 查找专业、班级
        }

    });

    return "";
    }
}
