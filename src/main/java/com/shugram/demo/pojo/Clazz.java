package com.shugram.demo.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Clazz {
    private Integer clazzId;
    private String clazzName;
    private String majorName;
    private String teacherName;
}
