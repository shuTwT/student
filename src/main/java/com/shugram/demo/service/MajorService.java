package com.shugram.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shugram.demo.entity.MajorEntity;

public interface MajorService extends IService<MajorEntity> {
    boolean haveMajor(String majorName);
    Long findIdByName(String majorName);
}
