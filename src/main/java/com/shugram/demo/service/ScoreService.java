package com.shugram.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shugram.demo.entity.ScoreEntity;
import com.shugram.demo.pojo.Course;
import com.shugram.demo.pojo.Score;

import java.util.List;

public interface ScoreService extends IService<ScoreEntity> {
    public List<Score> findScoreAll();
}
