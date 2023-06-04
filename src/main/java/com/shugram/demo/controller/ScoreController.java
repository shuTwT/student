package com.shugram.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shugram.demo.entity.ScoreEntity;
import com.shugram.demo.entity.StudentEntity;
import com.shugram.demo.pojo.Score;
import com.shugram.demo.service.LogService;
import com.shugram.demo.service.ScoreService;
import com.shugram.demo.utils.PassToken;
import com.shugram.demo.utils.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name="成绩接口")
@RequestMapping("score")
@RestController
public class ScoreController {
    private final ScoreService scoreService;
    private final LogService logService;

    @Autowired
    public ScoreController(ScoreService scoreService,LogService logService) {
        this.scoreService = scoreService;
        this.logService=logService;
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
    @Operation(summary = "批量导入成绩")
    @PostMapping("importScore")
    public Response<String> importScore(@RequestBody List<Map<String,String>> scoreList){
        return Response.success("success");
    }
    // 对指定成绩id增加十分
    @PostMapping("/increase")
    public Response<String> increaseScore(HttpServletRequest request, @RequestBody Map<String,String> params){
        String scoreId=params.get("scoreId");
        Long userId=Long.valueOf(params.get("userId"));
        scoreService.increaseScore(Long.valueOf(scoreId));
        HashMap<String,String> logMap=new HashMap<>();
        logMap.put("ip",request.getRemoteAddr());
        logMap.put("type","0");
        logMap.put("title","分数加10分");
        logMap.put("method","POST");
        logMap.put("user_id",userId+"");
        logService.insertLog(logMap);
        return Response.success("");
    }
    // 对指定成绩id减少十分
    @PostMapping("/reduce")
    public Response<String> reduceScore(HttpServletRequest request,@RequestBody Map<String,String> params){
        String scoreId=params.get("scoreId");
        Long userId=Long.valueOf(params.get("userId"));
        scoreService.reduceScore(Long.valueOf(scoreId));
        HashMap<String,String> logMap=new HashMap<>();
        logMap.put("ip",request.getRemoteAddr());
        logMap.put("type","0");
        logMap.put("title","分数减10分");
        logMap.put("method","POST");
        logMap.put("user_id",userId+"");
        logService.insertLog(logMap);
        return Response.success("");
    }

}
