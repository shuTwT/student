package com.shugram.demo.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shugram.demo.entity.ScoreEntity;
import com.shugram.demo.mapper.ScoreMapper;
import com.shugram.demo.pojo.Score;
import com.shugram.demo.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, ScoreEntity> implements ScoreService {
    private final ScoreMapper scoreMapper;

    @Autowired
    public ScoreServiceImpl(ScoreMapper scoreMapper) {
        this.scoreMapper = scoreMapper;
    }

    @Override
    public List<Score> findScoreAll(String courseName,String clazzName,int page,int size) {
        System.out.println(courseName);
        System.out.println(clazzName);
        return scoreMapper.findScoreAll(courseName,clazzName,page*size, size);
    }

    public boolean isHasScore(long stuId){
        return false;
    }

    public void increaseScore(Long scoreId){
        scoreMapper.inCreaseScore(scoreId);
    }
    public void reduceScore(Long scoreId){
        scoreMapper.reduceScore(scoreId);
    }

    public void resolveScoreList(List<Map<String,String>> scoreList){

    }

}
