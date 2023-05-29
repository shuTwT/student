package com.shugram.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shugram.demo.entity.StudentEntity;
import com.shugram.demo.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper extends BaseMapper<StudentEntity> {

    @Select("")
    List<Student> findStudentAll();
    // index 从第几条开始,num查询几条
    @Select("select stu_id,stu_name,major_name,stu_sex from student,stu_major where stu_major_id=major_id limit #{i},#{n};")
    List<Student> findStudentByPage(@Param("i") int i,@Param("n") int n);

    @Select("select count(*) from student")
    int findStudentCount();
}
