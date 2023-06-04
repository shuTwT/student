package com.shugram.demo.controller.system;

import com.shugram.demo.utils.Response;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("system")
@RestController
public class LogController {
    public Response<String> logList(){
        return Response.success("");
    }
}
