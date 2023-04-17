package com.shugram.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("course")
public class CourseEntity {
    @TableId
    private Long courseId;
    private String courseName;
    private Long courseTid;
    private Integer courseHour;
    private String courseScore;
}
