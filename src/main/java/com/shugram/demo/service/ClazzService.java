package com.shugram.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shugram.demo.entity.ClazzEntity;
import com.shugram.demo.pojo.Clazz;

import java.util.List;

public interface ClazzService extends IService<ClazzEntity> {
    List<Clazz> findClazzAll();
    Long findIdByName(String clazzName);
}
