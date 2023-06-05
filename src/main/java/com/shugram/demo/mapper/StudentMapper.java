package com.shugram.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shugram.demo.entity.StudentEntity;
import com.shugram.demo.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StudentMapper extends BaseMapper<StudentEntity> {

    @Select("")
    List<Student> findStudentAll();
    // index 从第几条开始,num查询几条
    @Select("select stu_id,stu_name,clazz_name,stu_sex from student,stu_clazz where stu_clazz_id=clazz_id and clazz_name LIKE '%${clazz_name}%' limit #{i},#{n};")
    List<Student> findStudentByPage(@Param("clazz_name") String clazzName,@Param("i") int i,@Param("n") int n);

    @Select("select count(*) from student")
    int findStudentCount();
}
