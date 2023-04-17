package com.shugram.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
@Data
@TableName("stu_major")
public class MajorEntity {
    @TableId
    private Long majorId;

    private String majorName;

    private String lastUpdate;


}
