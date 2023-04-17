package com.shugram.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("teacher")
public class TeacherEntity {
    @TableId
    private Long teacherId;
    private String teacherName;
}
