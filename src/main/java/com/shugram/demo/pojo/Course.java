package com.shugram.demo.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Course {
    private Integer courseId;
    private String courseName;
    private String teacherName;
    private Integer courseHour;
    private String courseScore;
}
