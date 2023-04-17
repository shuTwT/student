package com.shugram.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shugram.demo.entity.TeacherEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper extends BaseMapper<TeacherEntity> {
}
