package com.example.preprojec3.question.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class QuestionVotePostDto {

    private String voteStatus;
    private Long userId;
    private Long questionId;
}
