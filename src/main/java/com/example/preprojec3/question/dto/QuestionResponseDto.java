package com.example.preprojec3.question.dto;


import com.example.preprojec3.dto.QuestionStatus;
import com.example.preprojec3.question.entity.QuestionComment;
import com.example.preprojec3.question.entity.QuestionVote;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class QuestionResponseDto {


    private Long questionId;

    private String title;

    private String body;

    private int viewCounting;

    private QuestionStatus questionStatus;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private List<QuestionVoteResponseDto> questionVotes;

    private List<QuestionCommentResponseDto> questionComments;
}
