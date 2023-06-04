package com.shugram.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shugram.demo.entity.ScoreEntity;
import com.shugram.demo.pojo.Course;
import com.shugram.demo.pojo.Score;

import java.util.List;
import java.util.Map;

public interface ScoreService extends IService<ScoreEntity> {
    List<Score> findScoreAll(String courseName,String clazzName,int page,int size);
    void increaseScore(Long scoreId);
    void reduceScore(Long scoreId);
    void resolveScoreList(List<Map<String,String>> scoreList);
}
