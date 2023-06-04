package com.shugram.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shugram.demo.entity.ScoreEntity;
import com.shugram.demo.entity.StudentEntity;
import com.shugram.demo.pojo.Score;
import com.shugram.demo.service.ScoreService;
import com.shugram.demo.utils.PassToken;
import com.shugram.demo.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name="成绩接口")
@RequestMapping("score")
@RestController
public class ScoreController {
    private final ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }
    @PassToken
    @Operation(summary="成绩列表")
    @RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
    public Response<List<Score>> getList(@RequestParam Map<String,String> params ) {
        List<Score> scoreList= scoreService.findScoreAll();
        return Response.success(scoreList);
    }
    @Operation(summary="添加成绩")
    @PostMapping("/add")
    public Response<String> addStudent(@RequestBody ScoreEntity score){
        QueryWrapper<ScoreEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("SELECT 1")
                .eq("stu_id",score.getScoreId())
                .last("LIMIT 1");
        int count= scoreService.count(queryWrapper);
        if (count!=0){
            return Response.fail(400,"已存在数据");
        }else{
            scoreService.saveOrUpdate(score);
            return Response.success("");
        }
    }
    @Operation(summary="更新成绩信息")
    @PostMapping("/update")
    public Response<String> updateStudent(@RequestBody ScoreEntity score){
        QueryWrapper<ScoreEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("SELECT 1")
                .eq("stu_id",score.getScoreId())
                .last("LIMIT 1");
        int count= scoreService.count(queryWrapper);
        if (count==0){
            return Response.fail(400,"不存在数据");
        }else{
            scoreService.saveOrUpdate(score);
            return Response.success("");
        }
    }
    public Response<String> importScore(){
        return Response.success("success");
    }
    // 对指定成绩id增加十分
    @PostMapping("/increase")
    public Response<String> increaseScore(@RequestBody Map<String,String> params){
        String scoreId=params.get("scoreId");
        scoreService.increaseScore(Long.valueOf(scoreId));
        return Response.success("");
    }
    // 对指定成绩id减少十分
    @PostMapping("/reduce")
    public Response<String> reduceScore(@RequestBody Map<String,String> params){
        String scoreId=params.get("scoreId");
        scoreService.reduceScore(Long.valueOf(scoreId));
        return Response.success("");
    }

}
