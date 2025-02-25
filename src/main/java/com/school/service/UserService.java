package com.school.service;

import com.school.pojo.User;

public interface UserService {
    User findByUserName(String username);

    User findById(Integer id);


    void register(String username, String password);

    // 更新用户信息
    void update(User user);
    void participate(User user);
}
