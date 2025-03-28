package com.school.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Score {
    private Long id;
    private String username;
    private Integer score;
    private LocalDateTime createTime;
}
