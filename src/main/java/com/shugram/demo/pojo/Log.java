package com.shugram.demo.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Log {
    private Long logId;
    private String logTitle;
    private int logType;
    private String logIp;
    private String logMethod;
    private String logClient;
    private String userName;
    private String createTime;
}
