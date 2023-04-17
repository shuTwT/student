package com.shugram.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("sys_router")
public class RouterEntity {
    @TableId
    private Long routerId;
    private Long parentId;
    private String routerName;
    private String routerPath;
    private String routerComponent;
    private String routerIcon;
    private Integer minRole;
    @TableField(exist = false)
    private List<RouterEntity> children;

}
