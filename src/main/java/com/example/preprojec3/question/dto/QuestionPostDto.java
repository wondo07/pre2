package com.example.preprojec3.question.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class QuestionPostDto {

    private Long userId;
    private String title;
    private String body;

}
