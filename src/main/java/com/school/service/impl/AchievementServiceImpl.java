package com.school.service.impl;

import com.school.mapper.AchievementMapper;
import com.school.pojo.Achievement;
import com.school.service.AchievementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementServiceImpl implements AchievementService {

    @Autowired
    private AchievementMapper achievementMapper;

    @Override
    public void saveAchievement(Achievement achievement) {
        achievementMapper.saveAchievement(achievement);
    }

    @Override
    public List<Achievement> getAchievementsByUsername(String username) {
        return achievementMapper.getAchievementsByUsername(username);
    }
}
