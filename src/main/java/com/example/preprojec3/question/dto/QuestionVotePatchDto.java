package com.example.preprojec3.question.dto;


import com.example.preprojec3.dto.VoteStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class QuestionVotePatchDto {

    private String voteStatus;
    private Long userId;
    private Long questionId;

}
