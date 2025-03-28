package com.school.service;

import com.school.pojo.Score;

import java.util.List;

public interface ScoreService {
    void saveScore(Score score);
    List<Score> getTopScores(int limit);
    List<Score> getScoresByUsername(String username);
}
