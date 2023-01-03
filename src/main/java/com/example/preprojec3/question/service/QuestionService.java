package com.example.preprojec3.question.service;

import com.example.preprojec3.Exception.BusinessException;
import com.example.preprojec3.Exception.ErrorCode;
import com.example.preprojec3.question.entity.Question;
import com.example.preprojec3.question.repository.QuestionRepository;
import com.example.preprojec3.user.entity.User;
import com.example.preprojec3.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.preprojec3.dto.QuestionStatus.OPENED;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserService userService;


    public Question save(Question question, Long userId) {
        User user = userService.verifiedUser(userId);
        question.addUser(user);
        question.setViewCounting(0);
        question.setQuestionStatus(OPENED);
        questionRepository.save(question);

        return question;

    }

    public Question get(Long questionId) {
        Question question = verifiedQuestion(questionId);
        question.setViewCounting(question.getViewCounting()+1);
        return question;
    }





    public Question verifiedQuestion(Long questionId){
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        Question question = optionalQuestion.orElseThrow(
                ()-> new BusinessException(ErrorCode.USER_NOT_FOUND));
        return question;
    }


    public Page<Question> findAll(Pageable pageable) {
        return questionRepository.findAll(pageable);
    }

    public Question patch(Question question, Long questionId, Long userId) {
        User user = userService.verifiedUser(userId);
        Question findQuestion = verifiedQuestion(questionId);
        findQuestion.addUser(user);
        Optional.ofNullable(question.getTitle())
                .ifPresent(title -> findQuestion.setTitle(title));
        Optional.ofNullable(question.getBody())
                .ifPresent(body -> findQuestion.setBody(body));
        Optional.ofNullable(question.getQuestionStatus())
                .ifPresent(status -> findQuestion.setQuestionStatus(status));
        return findQuestion;
    }

    public void delete(Long questionId) {
        Question question = verifiedQuestion(questionId);
        questionRepository.delete(question);
    }

    public void deleteAll() {
        questionRepository.deleteAll();
    }
}
