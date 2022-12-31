package com.example.preprojec3.user.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserPostDto {

    private String email;
    private String password;
    private String displayname;
    private Boolean emailNotice;
}
