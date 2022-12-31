package com.example.preprojec3.user.dto;


import com.example.preprojec3.dto.LoginType;
import com.example.preprojec3.dto.UserStatus;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserResponseDto {


    private Long userId;

    private String email;

    private String password;

    private String displayname;

    private boolean emailNotice;

    private UserStatus userStatus;

    private LoginType loginType;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;


}
