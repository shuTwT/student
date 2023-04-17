package com.shugram.demo.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shugram.demo.entity.RouterEntity;
import com.shugram.demo.mapper.RouterMapper;
import com.shugram.demo.service.RouterService;
import org.springframework.stereotype.Service;

@Service
public class RouterServiceImpl extends ServiceImpl<RouterMapper, RouterEntity> implements RouterService {
}
