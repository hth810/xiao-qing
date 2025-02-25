package com.school.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    @NotNull
    private Long id;
    private String username;//学号

    private String realname;
    @JsonIgnore
    private String password;
    private String nickname;
    private String phoneNumber;
    @Email
    private String email;
    private String userPic;
    private Boolean participated;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
