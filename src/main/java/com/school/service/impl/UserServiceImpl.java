package com.school.service.impl;

import com.school.mapper.UserMapper;
import com.school.pojo.User;
import com.school.service.UserService;
import com.school.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        User u=userMapper.findByUserName(username);
        return u;
    }
    @Override
    public User findById(Integer id) {
        User u=userMapper.findById(id);
        return u;
    }
    @Override
    public void participate(User user) {
        userMapper.participate(user);
    }

    @Override
    public void register(String username, String password) {
        //加密密码
        String md5String= Md5Util.getMD5String(password);
        //添加
        userMapper.add(username,md5String);
    }
    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }


}
