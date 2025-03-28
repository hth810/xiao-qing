package com.school.service.impl;

import com.school.mapper.ScoreMapper;
import com.school.pojo.Score;
import com.school.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public void saveScore(Score score) {
        scoreMapper.saveScore(score);
    }
    @Override
    public List<Score> getTopScores(int limit) {
        return scoreMapper.getTopScores(limit);
    }

    @Override
    public List<Score> getScoresByUsername(String username) {
        return scoreMapper.getScoresByUsername(username);
    }
}
