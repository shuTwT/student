package com.shugram.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("score")
public class ScoreEntity {
    @TableId
    private Long scoreId;
    private Long scoreCid;
    private Long scoreSid;
    private String scoreNum;

}
