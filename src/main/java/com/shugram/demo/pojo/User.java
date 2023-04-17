package com.shugram.demo.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;
@Data
@Component
public class User {
    private String userName;
    private String password;
}
