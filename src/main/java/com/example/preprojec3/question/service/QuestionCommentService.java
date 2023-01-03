package com.example.preprojec3.question.service;


import com.example.preprojec3.Exception.BusinessException;
import com.example.preprojec3.Exception.ErrorCode;
import com.example.preprojec3.question.dto.QuestionCommentPatchDto;
import com.example.preprojec3.question.dto.QuestionCommentPostDto;
import com.example.preprojec3.question.entity.Question;
import com.example.preprojec3.question.entity.QuestionComment;
import com.example.preprojec3.question.repository.QuestionCommentRepository;
import com.example.preprojec3.user.entity.User;
import com.example.preprojec3.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionCommentService {

    private final QuestionCommentRepository questionCommentRepository;
    private final UserService userService;
    private final QuestionService questionService;

    public QuestionComment post(QuestionComment questionComment, QuestionCommentPostDto questionCommentPostDto) {
        User user = userService.verifiedUser(questionCommentPostDto.getUserId());
        Question question = questionService.verifiedQuestion(questionCommentPostDto.getQuestionId());
        questionComment.addQuestion(question);
        questionComment.addUser(user);
        QuestionComment save =  questionCommentRepository.save(questionComment);
        return save;


    }

    public QuestionComment patch(Long questionCommentId,
                                 QuestionComment questionComment,
                                 QuestionCommentPatchDto questionCommentPatchDto) {
        QuestionComment findQuestionComment = verifiedQuestionComment(questionCommentId);
        User user = userService.verifiedUser(questionCommentPatchDto.getUserId());
        Question question = questionService.verifiedQuestion(questionCommentPatchDto.getQuestionId());

        findQuestionComment.addUser(user);
        findQuestionComment.addQuestion(question);

        Optional.ofNullable(questionComment.getComment())
                .ifPresent(comment -> findQuestionComment.setComment(comment));

        return findQuestionComment;

    }


    public QuestionComment verifiedQuestionComment(Long questionCommentId){
        Optional<QuestionComment> findQuestionComment = questionCommentRepository.findById(questionCommentId);
        QuestionComment questionComment = findQuestionComment.orElseThrow(
                () -> new BusinessException(ErrorCode.QUESTION_NOT_FOUND));
        return questionComment;
    }

    public void delete(Long questionCommentId) {
        QuestionComment questionComment = verifiedQuestionComment(questionCommentId);
        questionCommentRepository.delete(questionComment);
    }
}
