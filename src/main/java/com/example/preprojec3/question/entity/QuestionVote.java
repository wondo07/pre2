package com.example.preprojec3.question.entity;


import com.example.preprojec3.dto.VoteStatus;
import com.example.preprojec3.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "createAt"),
        @Index(columnList = "updateAt")
})
@EntityListeners(AuditingEntityListener.class)
@Entity(name="QUESTION_VOTE")
@NoArgsConstructor
public class QuestionVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionVoteId;
    @Enumerated(value = EnumType.STRING)
    @Setter
    @Column(nullable = false)
    private VoteStatus voteStatus;

    @Column(nullable = false, insertable = false, updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    @CreatedDate
    private LocalDateTime createAt;

    @Column(nullable = false, insertable = false, updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    @LastModifiedDate
    private LocalDateTime updateAt;


    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @Setter
    private User user;

    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @Setter
    private Question question;


    public void addQuestion(Question question){
        this.question=question;
        question.addQuestionVote(this);
    }

    public void addUser(User user){
        this.user=user;
        user.addQuestionVote(this);
    }

}
