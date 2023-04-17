package com.shugram.demo.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shugram.demo.entity.UserEntity;
import com.shugram.demo.mapper.UserMapper;
import com.shugram.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    private final UserMapper userMapper;
    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<UserEntity> getUserById(int userId){
        return userMapper.getUserById(userId);
    }
}
