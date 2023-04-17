package com.shugram.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shugram.demo.entity.MajorEntity;
import com.shugram.demo.service.MajorService;
import com.shugram.demo.utils.PassToken;
import com.shugram.demo.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="专业接口")
@RequestMapping("major")
@RestController
public class MajorController {
    private final MajorService majorService;
    @Autowired
    public MajorController(MajorService majorService) {
        this.majorService = majorService;
    }
    @PassToken
    @Operation(summary="专业列表")
    @RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
    public Response<List<MajorEntity>> getList() {
        List<MajorEntity> majorList= majorService.list();
        return Response.success(majorList);
    }
    @Operation(summary="添加专业")
    @PostMapping("/add")
    public Response<String> addStudent(@RequestBody MajorEntity major){
        QueryWrapper<MajorEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("SELECT 1")
                .eq("stu_id",major.getMajorId())
                .last("LIMIT 1");
        int count= majorService.count(queryWrapper);
        if (count!=0){
            return Response.fail(400,"已存在数据");
        }else{
            majorService.saveOrUpdate(major);
            return Response.success("");
        }
    }
    @Operation(summary="更新专业信息")
    @PostMapping("/update")
    public Response<String> updateStudent(@RequestBody MajorEntity major){
        QueryWrapper<MajorEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("SELECT 1")
                .eq("stu_id",major.getMajorId())
                .last("LIMIT 1");
        int count= majorService.count(queryWrapper);
        if (count==0){
            return Response.fail(400,"不存在数据");
        }else{
            majorService.saveOrUpdate(major);
            return Response.success("");
        }
    }
}
