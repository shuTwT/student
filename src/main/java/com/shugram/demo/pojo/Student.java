package com.shugram.demo.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Student {
    private Long stuId;
    private String stuName;
    private String majorName;
    private String stuSex;
}
