package com.example.preprojec3.question.dto;


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
public class QuestionCommentResponseDto {


    private Long questionCommentId;

    private String comment;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;


}
