package com.example.preprojec3.user.dto;


import com.example.preprojec3.dto.UserStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserPatchDto {


    private String password;
    private String displayName;
    private UserStatus userNotice;
}
