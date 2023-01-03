package com.example.preprojec3.question.controller;


import com.example.preprojec3.dto.SingleResponseDto;
import com.example.preprojec3.question.dto.QuestionCommentPatchDto;
import com.example.preprojec3.question.dto.QuestionCommentPostDto;
import com.example.preprojec3.question.dto.QuestionCommentResponseDto;
import com.example.preprojec3.question.entity.QuestionComment;
import com.example.preprojec3.question.mapper.QuestionMapper;
import com.example.preprojec3.question.service.QuestionCommentService;
import com.example.preprojec3.question.service.QuestionService;
import com.example.preprojec3.question.service.QuestionVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questioncomment")
@RequiredArgsConstructor
public class QuestionCommentController {

    private final QuestionMapper questionMapper;
    private final QuestionCommentService questionCommentService;

    // 답변 생성
    @PostMapping
    public ResponseEntity QuestionCommentPost(@RequestBody QuestionCommentPostDto questionCommentPostDto){
        QuestionComment questionComment =
                questionMapper.QuestionCommentPostDtoToEntity(questionCommentPostDto);
        QuestionComment save = questionCommentService.post(questionComment,questionCommentPostDto);
        QuestionCommentResponseDto questionCommentResponseDto =
                questionMapper.QuestionCommentEntityToReponseDto(save);

        return new ResponseEntity<>(
                SingleResponseDto.of(questionCommentResponseDto), HttpStatus.CREATED);
    }

    // 답변 수정
    @PatchMapping("/{questionCommentId}")
    public ResponseEntity QuestionCommentPatch(@PathVariable Long questionCommentId,
                                               @RequestBody QuestionCommentPatchDto questionCommentPatchDto){

        QuestionComment questionComment =
                questionMapper.QuestionCommentPatchDtoToEntity(questionCommentPatchDto);
        QuestionComment patch = questionCommentService.patch(questionCommentId,
                            questionComment,questionCommentPatchDto);
        QuestionCommentResponseDto questionCommentResponseDto =
                questionMapper.QuestionCommentEntityToReponseDto(patch);

        return new ResponseEntity<>(
                SingleResponseDto.of(questionCommentResponseDto),HttpStatus.OK);

    }

    // 답변 삭제
    @DeleteMapping("/{questionCommentId}")
    public ResponseEntity QuestionDelete(@PathVariable Long questionCommentId){
        questionCommentService.delete(questionCommentId);
        return ResponseEntity.noContent().build();
    }




}
