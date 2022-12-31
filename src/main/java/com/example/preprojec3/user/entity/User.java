package com.example.preprojec3.user.entity;

import com.example.preprojec3.dto.LoginType;
import com.example.preprojec3.dto.UserStatus;
import com.example.preprojec3.question.entity.Question;
import com.example.preprojec3.question.entity.QuestionComment;
import com.example.preprojec3.question.entity.QuestionVote;
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

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "createAt"),
        @Index(columnList = "updateAt")
}, name = "USERS")
@EntityListeners(AuditingEntityListener.class)
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    @Setter
    private String email;

    @Column(nullable = false)
    @Setter
    private String password;

    @Column(nullable = false)
    @Setter
    private String displayname;

    @Column(nullable = false)
    @Setter
    private boolean emailNotice;

    @Setter
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserStatus userStatus;


    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    @Setter
    private LoginType loginType;

    @Column(nullable = false, insertable = false, updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    @CreatedDate
    private LocalDateTime createAt;

    @Column(nullable = false, insertable = false, updatable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP")
    @LastModifiedDate
    private LocalDateTime updateAt;

    @OrderBy("questionId")
    @OneToMany(mappedBy = "user")
    List<Question> questions = new ArrayList<>();

    @OrderBy("questionCommentId")
    @OneToMany(mappedBy = "questioncomment")
    List<QuestionComment> questionComments = new ArrayList<>();

    @OrderBy("questionVoteId")
    @OneToMany(mappedBy = "questionvote")
    List<QuestionVote> questionVotes = new ArrayList<>();


    public void addQuestionVote(QuestionVote questionVote){
        questionVotes.add(questionVote);
    }

    public void addQuestion(Question question){
        questions.add(question);
    }
    public void addQuestionComment(QuestionComment questionComment){
        questionComments.add(questionComment);
    }

}
