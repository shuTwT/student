package com.shugram.demo.controller;

import com.shugram.demo.pojo.Clazz;
import com.shugram.demo.service.ClazzService;
import com.shugram.demo.utils.PassToken;
import com.shugram.demo.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="班级接口")
@RequestMapping("clazz")
@RestController
public class ClazzController {
    private final ClazzService clazzService;
    @Autowired
    public ClazzController(ClazzService clazzService) {
        this.clazzService = clazzService;
    }


    @PassToken
    @Operation(summary="课程列表")
    @RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
    public Response<List<Clazz>> getList() {
        List<Clazz> clazzList= clazzService.findClazzAll();
        return Response.success(clazzList);
    }
}
