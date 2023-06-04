package com.shugram.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shugram.demo.entity.LogEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LogMapper extends BaseMapper<LogEntity> {
    @Insert("INSERT INTO sys_log (log_content,user_id) VALUES (#{content},#{user_id})")
    void insertLog(@Param("content") String content,@Param("user_id") Long userId);
}
