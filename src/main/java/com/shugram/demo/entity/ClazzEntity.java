package com.shugram.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("stu_clazz")
public class ClazzEntity {
    @TableId
    private Long clazzId;
    private String clazzName;
}
