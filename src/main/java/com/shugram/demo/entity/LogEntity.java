package com.shugram.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_log")
public class LogEntity {
    @TableId
    private Long logId;
    private String logContent;
    private Long userId;
}
