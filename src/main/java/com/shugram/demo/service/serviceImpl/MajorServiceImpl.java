package com.shugram.demo.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shugram.demo.entity.MajorEntity;
import com.shugram.demo.mapper.MajorMapper;
import com.shugram.demo.service.MajorService;
import org.springframework.stereotype.Service;

@Service
public class MajorServiceImpl extends ServiceImpl<MajorMapper, MajorEntity> implements MajorService {
}
