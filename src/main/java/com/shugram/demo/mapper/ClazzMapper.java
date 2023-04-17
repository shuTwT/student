package com.shugram.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shugram.demo.entity.ClazzEntity;
import com.shugram.demo.pojo.Clazz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper extends BaseMapper<ClazzEntity> {
    @Select("SELECT clazz_id,clazz_name,major_name,teacher_name FROM stu_clazz,stu_major,teacher where stu_clazz.major_id=stu_major.major_id and assistant_id=teacher_id;")
    List<Clazz> findClazzAll();
}
