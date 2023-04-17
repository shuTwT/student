package com.shugram.demo.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shugram.demo.entity.ClazzEntity;
import com.shugram.demo.mapper.ClazzMapper;
import com.shugram.demo.pojo.Clazz;
import com.shugram.demo.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, ClazzEntity> implements ClazzService {
    private final ClazzMapper clazzMapper;

    @Autowired
    public ClazzServiceImpl(ClazzMapper clazzMapper) {
        this.clazzMapper = clazzMapper;
    }

    @Override
    public List<Clazz> findClazzAll() {
        return clazzMapper.findClazzAll();
    }
}
