package com.example.preprojec3.question.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class QuestionPatchDto {

    private String title;
    private String body;
    private String questionStatus;
    private Long userId;
}
