package com.example.preprojec3.question.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class QuestionCommentPostDto {

    private Long userId;
    private Long questionId;
    private String comment;
}
