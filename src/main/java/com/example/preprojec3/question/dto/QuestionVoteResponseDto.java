package com.example.preprojec3.question.dto;


import com.example.preprojec3.dto.VoteStatus;
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
public class QuestionVoteResponseDto {



    private Long questionVoteId;

    private VoteStatus voteStatus;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;
}
