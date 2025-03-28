package com.school.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Achievement {
    private Long id;
    private String username;
    private String achievementName;
    private LocalDateTime createTime;

}
