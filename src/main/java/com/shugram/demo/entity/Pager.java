package com.shugram.demo.entity;

import lombok.Data;

import java.util.List;

@Data
public class Pager<T> {
    private int page;//第几页
    private int size;//每页记录数
    private List<T> data;//返回的记录集合
    private int total;//总记录条数
    public Pager(){}

    public Pager(int page,int size){
        this.page=page;
        this.size=size;
    }
    @Override
    public String toString(){
        return "Pager{" +
                "page=" + page +
                ", size=" + size +
                ", data=" + data +
                ", total=" + total +
                '}';
    }
}
