package com.school.service;


import com.school.pojo.Achievement;

import java.util.List;

public interface AchievementService {
    void saveAchievement(Achievement achievement);
    List<Achievement> getAchievementsByUsername(String username);
}
