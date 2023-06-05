package com.shugram.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class UserEntity {
    @TableId("user_id")
    private Integer id;
    @TableField("user_name")
    private String userName;
    @TableField("user_password")
    private String password;
    @TableField("role_id")
    private Integer roleId;

}
