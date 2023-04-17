package com.shugram.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shugram.demo.entity.StudentEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper extends BaseMapper<StudentEntity> {

}
