package com.example.preprojec3.user.dto;

import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserPageResponseDto {

    private Long userId;

    private String email;

    private String displayName;

    private Boolean emailNotice;

    private String userStatus;

    private String loginType;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
