package com.shugram.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("student")
public class StudentEntity {
    @TableId
    private Long stuId;
    private String stuName;
    private String stuAge;
    private String stuSex;
    private String stuDate;
    private Long stuMajorId;
    private Long stuClazzId;
    private String lastUpdate;
}
