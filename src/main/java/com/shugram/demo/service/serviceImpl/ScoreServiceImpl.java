package com.shugram.demo.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shugram.demo.entity.Pager;
import com.shugram.demo.entity.ScoreEntity;
import com.shugram.demo.mapper.ScoreMapper;
import com.shugram.demo.pojo.Score;
import com.shugram.demo.service.ClazzService;
import com.shugram.demo.service.CourseService;
import com.shugram.demo.service.ScoreService;
import com.shugram.demo.service.StudentService;
import com.shugram.demo.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, ScoreEntity> implements ScoreService {
    private final ScoreMapper scoreMapper;
    private final CourseService courseService;
    private final ClazzService clazzService;
    private final StudentService studentService;

    @Autowired
    public ScoreServiceImpl(ScoreMapper scoreMapper, CourseService courseService, ClazzService clazzService,StudentService studentService) {
        this.scoreMapper = scoreMapper;
        this.courseService = courseService;
        this.clazzService = clazzService;
        this.studentService=studentService;
    }

    @Override
    public Response<Pager<Score>> findScoreAll(String courseName, String clazzName, int page, int size) {
        Pager<Score> pager = new Pager<>(page, size);
        pager.setData(scoreMapper.findScoreAll(courseName, clazzName, page * size, size));
        pager.setTotal(scoreMapper.findScoreCount());
        return Response.success(pager);
    }

    public boolean isHasScore(long stuId) {
        return false;
    }

    public void increaseScore(Long scoreId) {
        scoreMapper.inCreaseScore(scoreId);
    }

    public void reduceScore(Long scoreId) {
        scoreMapper.reduceScore(scoreId);
    }

    public Response<String> resolveScoreList(List<Map<String, String>> scoreList) {
        scoreList.forEach(item -> {
            Long courseId=courseService.findIdByName(item.get("课程名"));
            Long stuId=studentService.findIdByName(item.get("学生名"));
            Long clazzId=clazzService.findIdByName(item.get("班级名"));
            String scoreNum= "";
            if ("".equals(item.get("分数"))){
                scoreNum="50";
            }else {
                scoreNum=item.get("分数");
            }
            ScoreEntity scoreEntity = new ScoreEntity();
            scoreEntity.setScoreNum(scoreNum);
            scoreEntity.setScoreCid(courseId);
            scoreEntity.setScoreSid(stuId);
            scoreEntity.setScoreCid(clazzId);
            this.save(scoreEntity);
        });
        return Response.success("success");
    }
    public Response<String> addScore(){
        return Response.success("");
    }

}
