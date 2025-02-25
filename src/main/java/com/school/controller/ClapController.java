package com.school.controller;

import com.school.mapper.ClapMapper;
import com.school.mapper.UserMapper;
import com.school.pojo.Result;
import com.school.pojo.User;
import com.school.service.ClapService;
import com.school.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clap")
public class ClapController {

    @Autowired
    private UserService userService;
    @Autowired
    private ClapService clapService;

    @PostMapping("/participate")
    public Result<Boolean> participate(@RequestBody @Validated User user) {
        if (user.getParticipated()==false) {
            String username = user.getUsername();
            user.setParticipated(true);
            userService.participate(user);
            clapService.participate(username);
            return Result.success();
        }else {
            return Result.error("您已参与过活动");
        }

    }
    @GetMapping("/total-participants")
    public Result<Long> getTotalParticipants() {
        long totalParticipants = clapService.getTotalParticipants();
        return Result.success(totalParticipants);
    }

}
