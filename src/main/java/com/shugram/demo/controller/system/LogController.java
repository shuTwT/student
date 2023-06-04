package com.shugram.demo.controller.system;

import com.shugram.demo.entity.LogEntity;
import com.shugram.demo.entity.Pager;
import com.shugram.demo.pojo.Log;
import com.shugram.demo.service.LogService;
import com.shugram.demo.utils.PassToken;
import com.shugram.demo.utils.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequestMapping("system/log")
@RestController
public class LogController {
    private final LogService logService;
    public LogController(LogService logService){
        this.logService=logService;
    }
    @PassToken
    @RequestMapping("/list")
    public Response<Pager<Log>> logList(@RequestParam Map<String,String> params){
        int page=Integer.parseInt(params.get("page"));
        int limit=Integer.parseInt(params.get("limit"));
        return logService.logList(page,limit);
    }
}
