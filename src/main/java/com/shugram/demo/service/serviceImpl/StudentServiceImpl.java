package com.shugram.demo.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shugram.demo.entity.Pager;
import com.shugram.demo.entity.StudentEntity;
import com.shugram.demo.mapper.StudentMapper;
import com.shugram.demo.pojo.Student;
import com.shugram.demo.service.ClazzService;
import com.shugram.demo.service.MajorService;
import com.shugram.demo.service.StudentService;
import com.shugram.demo.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, StudentEntity> implements StudentService {

    private final StudentMapper studentMapper;
    private final MajorService majorService;

    private final ClazzService clazzService;

    @Autowired
    public StudentServiceImpl(MajorService majorService, ClazzService clazzService, StudentMapper studentMapper) {
        this.majorService = majorService;
        this.clazzService = clazzService;
        this.studentMapper = studentMapper;
    }

    public Response<String> resolveStudentList(List<Map<String, String>> studentList) {
        System.out.println(studentList);
        studentList.forEach(item -> {
            // 通过学号判断是否存在该学生
            int count = this.count(new LambdaQueryWrapper<StudentEntity>()
                    .exists("select stu_id from student where stu_id = '" + item.get("学生学号") + "'")
            );
            if (count > 0) {
                // 该学生存在
                // 查找专业、班级
                Long majorId = majorService.findIdByName(item.get("专业"));
                Long clazzId = clazzService.findIdByName(item.get("班级"));
                // 检查学号对应的分数是否存在
                StudentEntity studentEntity = new StudentEntity();
                studentEntity.setStuId(Long.valueOf(item.get("学生学号")));
                studentEntity.setStuName(item.get("学生姓名"));
                studentEntity.setStuMajorId(majorId);
                studentEntity.setStuClazzId(clazzId);
                studentEntity.setStuSex("男");
                studentEntity.setStuDate("2021-01-01");
                this.updateById(studentEntity);

            } else {
                // 该学生不存在
                Long majorId = majorService.findIdByName(item.get("专业"));
                Long clazzId = clazzService.findIdByName(item.get("班级"));
                StudentEntity studentEntity = new StudentEntity();
                studentEntity.setStuId(Long.valueOf(item.get("学生学号")));
                studentEntity.setStuName(item.get("学生姓名"));
                studentEntity.setStuMajorId(majorId);
                studentEntity.setStuClazzId(clazzId);
                studentEntity.setStuSex("男");
                studentEntity.setStuDate("2021-01-01");
                this.save(studentEntity);
            }

        });

        return Response.success("success");
    }

    // page第几页
    // size每页记录数
    public Pager<Student> getStudentListByPage(int page,int size) {
        Pager<Student> pager= new Pager<>(page,size);
        pager.setData(studentMapper.findStudentByPage(page*size,size));
        pager.setTotal(studentMapper.findStudentCount());
        System.out.println("这里");
        System.out.println(pager.getData());
        return pager;
    }
    public Long findIdByName(String stuName){
        StudentEntity one= this.getOne(new QueryWrapper<StudentEntity>()
                .eq("stu_name",stuName)
                .last("limit 1")
        );
        return one.getStuId();
    }


}
