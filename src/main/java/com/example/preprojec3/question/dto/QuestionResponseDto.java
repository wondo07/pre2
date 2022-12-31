package com.example.preprojec3.question.dto;


import com.example.preprojec3.dto.QuestionStatus;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class QuestionResponseDto {


    private Long questionId;

    private String title;

    private String body;

    private QuestionStatus questionStatus;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
