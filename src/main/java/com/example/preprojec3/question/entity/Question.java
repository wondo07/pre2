package com.example.preprojec3.question.entity;


import com.example.preprojec3.dto.QuestionStatus;
import com.example.preprojec3.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.core.annotation.Order;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "createAt"),
        @Index(columnList = "updateAt")
}, name = "QUESTIONS")
@EntityListeners(AuditingEntityListener.class)
@Entity
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String body;
    @Column(nullable = false)
    private QuestionStatus questionStatus;

    @Column(nullable = false, insertable = false, updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    @CreatedDate
    private LocalDateTime createAt;

    @Column(nullable = false, insertable = false, updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    @LastModifiedDate
    private LocalDateTime updateAt;

    @ManyToOne(optional = true,fetch = FetchType.LAZY)
    @Setter
    private User user;

    @OneToMany(mappedBy = "question")
    @OrderBy("questionCommentId")
    List<QuestionComment> questionComments = new ArrayList<>();

    @OneToMany(mappedBy = "question")
    @OrderBy("questionVoteId")
    List<QuestionVote> questionVotes = new ArrayList<>();

    public void addQuestionVote(QuestionVote questionVote){
        questionVotes.add(questionVote);
    }

    public void addQuestionComment(QuestionComment questionComment){
        questionComments.add(questionComment);
    }

    public void addUser(User user){
        this.user = user;
        user.addQuestion(this);
    }


}
