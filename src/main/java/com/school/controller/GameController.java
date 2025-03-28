package com.school.controller;

import com.school.pojo.Achievement;
import com.school.pojo.Result;
import com.school.pojo.Score;
import com.school.service.AchievementService;
import com.school.service.ScoreService;
import com.school.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private AchievementService achievementService;

    @PostMapping("/score")
    public Result saveScore(@RequestBody Score score) {
        scoreService.saveScore(score);
        return Result.success();
    }

    @PostMapping("/achievement")
    public Result saveAchievement(@RequestBody Achievement achievement){
        achievementService.saveAchievement(achievement);
        return Result.success();
    }

    @GetMapping("/scores")
    public Result<List<Score>> getTopScores(@RequestParam(defaultValue = "10") int limit) {
        List<Score> topScores = scoreService.getTopScores(limit);
        return Result.success(topScores);
    }

    @GetMapping("/scores/user")
    public Result<List<Score>> getScoresByUsername(@RequestParam String username) {
        List<Score> scores = scoreService.getScoresByUsername(username);
        if (scores.isEmpty()) {
            return Result.error("未参与");
        }
        return Result.success(scores);
    }

    @GetMapping("/achievements/user")
    public Result<List<Achievement>> getAchievementsByUsername(@RequestParam String username) {
        List<Achievement> achievements = achievementService.getAchievementsByUsername(username);
        if (achievements.isEmpty()) {
            return Result.error("未获得");
        }
        return Result.success(achievements);
    }
}
