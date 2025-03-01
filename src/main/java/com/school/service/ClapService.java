package com.school.service;


import com.school.pojo.Clap;

public interface ClapService {
    Clap findByUserName(String username);

    void participate(String username);

    long getTotalParticipants();
}
