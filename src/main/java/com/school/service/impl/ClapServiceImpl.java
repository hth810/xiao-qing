package com.school.service.impl;

import com.school.mapper.ClapMapper;
import com.school.mapper.UserMapper;
import com.school.pojo.Clap;
import com.school.pojo.User;
import com.school.service.ClapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClapServiceImpl implements ClapService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ClapMapper clapMapper;


    @Override
    public void participate(String username) {
        clapMapper.participate(username);

    }
    @Override
    public long getTotalParticipants() {
        return clapMapper.getTotalParticipants();
    }
}
