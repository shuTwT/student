package com.shugram.demo.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shugram.demo.entity.TeacherEntity;
import com.shugram.demo.mapper.TeacherMapper;
import com.shugram.demo.service.TeacherService;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl  extends ServiceImpl<TeacherMapper, TeacherEntity> implements TeacherService {
}
