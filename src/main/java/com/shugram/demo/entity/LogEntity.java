package com.shugram.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_log")
public class LogEntity {
    @TableId
    private Long logId;
    private String logTitle;
    private int logType;
    private String logIp;
    private String logMethod;
    private String logClient;
    private Long userId;

}
