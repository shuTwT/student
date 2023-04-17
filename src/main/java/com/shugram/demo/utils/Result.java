package com.shugram.demo.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Result implements Serializable {

    private List<Result> children;
    //父id
    private Integer parentId;
    //当前Id
    private Integer id;

    private String name;

}
