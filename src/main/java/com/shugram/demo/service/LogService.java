package com.shugram.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shugram.demo.entity.LogEntity;
import com.shugram.demo.entity.Pager;
import com.shugram.demo.pojo.Log;
import com.shugram.demo.utils.Response;

import java.util.Map;

public interface LogService extends IService<LogEntity> {
    void insertLog(Map<String,String> logMap);
    Response<Pager<Log>> logList(int page, int size);
}
