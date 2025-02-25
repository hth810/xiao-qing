package com.school.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Clap {
    private Long id;
    private int userId;
    private LocalDateTime createTime;
}
