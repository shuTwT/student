package com.shugram.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shugram.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    @Select("SELECT COUNT(*) FROM sys_user where user_name = #{user_name};")
    int getCountByName(@Param("user_name") String userName);
    @Select("SELECT COUNT(*) FROM sys_user WHERE user_name = #{user_name} and user_password = #{password};")
    int getCountByPassword(@Param("user_name") String userName,@Param("user_password") String userPassword);
    @Select("SELECT user_id,user_name,user_password FROM sys_user where user_id= #{user_id};")
    List<UserEntity> getUserById(@Param("user_id")int userId);
}
