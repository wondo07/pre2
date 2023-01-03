package com.example.preprojec3.question.service;


import com.example.preprojec3.Exception.BusinessException;
import com.example.preprojec3.Exception.ErrorCode;
import com.example.preprojec3.dto.VoteStatus;
import com.example.preprojec3.question.dto.QuestionVotePatchDto;
import com.example.preprojec3.question.dto.QuestionVotePostDto;
import com.example.preprojec3.question.dto.QuestionVoteResponseDto;
import com.example.preprojec3.question.entity.Question;
import com.example.preprojec3.question.entity.QuestionVote;
import com.example.preprojec3.question.repository.QuestionVoteRepository;
import com.example.preprojec3.user.entity.User;
import com.example.preprojec3.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionVoteService {

    private final QuestionVoteRepository questionVoteRepository;
    private final UserService userService;
    private final QuestionService questionService;

    public QuestionVote post(QuestionVote questionVote, QuestionVotePostDto questionVotePostDto) {
        User user = userService.verifiedUser(questionVotePostDto.getUserId());
        Question question = questionService.verifiedQuestion(questionVotePostDto.getQuestionId());
        questionVote.addUser(user);
        questionVote.addQuestion(question);

        QuestionVote save = questionVoteRepository.save(questionVote);
        return save;

    }

    public QuestionVote verifiedQuestionVote(Long questionVoteId){
        Optional<QuestionVote> optionalQuestionVote = questionVoteRepository.findById(questionVoteId);
        QuestionVote questionVote = optionalQuestionVote.orElseThrow(
                () -> new BusinessException(ErrorCode.QUESTION_NOT_FOUND));
        return questionVote;
    }

    public QuestionVote patch(QuestionVote questionVote, Long questionVoteId, QuestionVotePatchDto questionVotePatchDto) {
        QuestionVote findQuestionVote = verifiedQuestionVote(questionVoteId);
        VoteStatus status = questionVote.getVoteStatus();

        if(status!=findQuestionVote.getVoteStatus()) {
            if(status==VoteStatus.UP){
                findQuestionVote.setVoteStatus(VoteStatus.UP);
            } else if (status == VoteStatus.DOWN) {
                findQuestionVote.setVoteStatus(VoteStatus.DOWN);
            } else {
                findQuestionVote.setVoteStatus(VoteStatus.NONE);
            }
        }
        return findQuestionVote;
    }
}
