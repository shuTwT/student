package com.shugram.demo.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Score {
    private Integer scoreId;
    private String courseName;
    private String clazzName;
    private String stuName;
    private String scoreNum;
}
