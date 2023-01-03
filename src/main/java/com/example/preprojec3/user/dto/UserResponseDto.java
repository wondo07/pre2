package com.example.preprojec3.user.dto;


import com.example.preprojec3.dto.LoginType;
import com.example.preprojec3.dto.UserStatus;
import com.example.preprojec3.question.dto.QuestionResponseDto;
import com.example.preprojec3.question.entity.Question;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserResponseDto {


    private Long userId;

    private String email;

    private String password;

    private String displayName;

    private boolean emailNotice;

    private UserStatus userStatus;

    private LoginType loginType;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private List<QuestionResponseDto> questions;

}
