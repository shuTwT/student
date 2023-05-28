package com.shugram.demo.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shugram.demo.entity.MajorEntity;
import com.shugram.demo.mapper.MajorMapper;
import com.shugram.demo.service.MajorService;
import org.springframework.stereotype.Service;

@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, MajorEntity> implements MajorService {
    public boolean haveMajor(String majorName){
        int count=this.count(new LambdaQueryWrapper<MajorEntity>()
                .exists("select stu_name from stu_major where major_name = '"+majorName+"'")
        );
        return count > 0;
    }
    public Long findIdByName(String majorName){
        MajorEntity one= this.getOne(new QueryWrapper<MajorEntity>()
                .eq("major_name",majorName)
                .last("limit 1")
        );
        return one.getMajorId();
    }
}
