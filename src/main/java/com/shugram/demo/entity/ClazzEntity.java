package com.shugram.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("stu_clazz")
public class ClazzEntity {
    private Long clazzId;
    private String clazzName;
}
