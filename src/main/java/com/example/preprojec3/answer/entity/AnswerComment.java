//package com.example.preprojec3.answer.entity;
//
//
//import com.example.preprojec3.question.entity.QuestionComment;
//import com.example.preprojec3.question.entity.QuestionVote;
//import lombok.*;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//@ToString
//@Table(indexes = {
//        @Index(columnList = "createAt"),
//        @Index(columnList = "updateAt")
//})
//@EntityListeners(AuditingEntityListener.class)
//@Entity
//@NoArgsConstructor
//public class AnswerComment {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long answerCommentId;
//    @Column(nullable = false)
//    @Setter
//    private String comment;
//
//
//    @OneToMany(mappedBy = "question",cascade = CascadeType.REMOVE)
//    @OrderBy("questionCommentId")
//    List<QuestionComment> questionComments = new ArrayList<>();
//
//    @OneToMany(mappedBy = "question",cascade = CascadeType.REMOVE)
//    @OrderBy("questionVoteId")
//    List<QuestionVote> questionVotes = new ArrayList<>();
//}
