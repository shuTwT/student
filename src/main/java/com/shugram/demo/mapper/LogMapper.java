package com.shugram.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shugram.demo.entity.LogEntity;
import com.shugram.demo.pojo.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface LogMapper extends BaseMapper<LogEntity> {
    @Insert("INSERT INTO sys_log (log_title,log_type,log_ip,log_method,user_id) VALUES (#{log_title},#{log_type},#{log_ip},#{log_method},#{user_id})")
    void insertLog(@Param("log_title") String LogTitle,@Param("log_ip") String logIp,@Param("log_type") int logType,@Param("log_method") String logMethod,@Param("user_id") Long userId);

    @Select("select log_id,log_title,log_type,log_ip,log_method,log_client,user_name,create_time from sys_log,sys_user where sys_log.user_id=sys_user.user_id limit #{i},#{n}")
    List<Log> logList(@Param("i") int i,@Param("n") int n);
    @Select("select count(*) from sys_log")
    int findLogCount();
}
