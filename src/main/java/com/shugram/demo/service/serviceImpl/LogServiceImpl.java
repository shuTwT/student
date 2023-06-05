package com.shugram.demo.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shugram.demo.entity.LogEntity;
import com.shugram.demo.entity.Pager;
import com.shugram.demo.mapper.LogMapper;
import com.shugram.demo.pojo.Log;
import com.shugram.demo.service.LogService;
import com.shugram.demo.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, LogEntity> implements LogService {
    private final  LogMapper logMapper;
    @Autowired
    public LogServiceImpl(LogMapper logMapper){
        this.logMapper=logMapper;
    }


    public void insertLog(Map<String,String> logMap) {
        String title=logMap.get("title");
        String ip=logMap.get("ip");
        int type=Integer.parseInt(logMap.get("type"));
        String method=logMap.get("method");
        Long userId=Long.valueOf(logMap.get("user_id"));
        logMapper.insertLog(title,ip,type,method,userId);
    }
    public Response<Pager<Log>> logList(int page, int size){
        Pager<Log> pager=new Pager<>(page,size);
        pager.setData(logMapper.logList(page*size,size));
        pager.setTotal(logMapper.findLogCount());
        return Response.success(pager);
    }
}
