package com.example.preprojec3.question.controller;


import com.example.preprojec3.dto.SingleResponseDto;
import com.example.preprojec3.question.dto.QuestionVotePatchDto;
import com.example.preprojec3.question.dto.QuestionVotePostDto;
import com.example.preprojec3.question.dto.QuestionVoteResponseDto;
import com.example.preprojec3.question.entity.QuestionVote;
import com.example.preprojec3.question.mapper.QuestionMapper;
import com.example.preprojec3.question.service.QuestionVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questionvote")
@RequiredArgsConstructor
public class QuestionVoteController {

    private final QuestionVoteService questionVoteService;
    private final QuestionMapper questionMapper;

    @PostMapping
    private ResponseEntity QuestionVotepost(@RequestBody QuestionVotePostDto questionVotePostDto){
       QuestionVote questionVote = questionMapper.QuestionVotePostDtoToEntity(questionVotePostDto);
       QuestionVote save  = questionVoteService.post(questionVote,questionVotePostDto);
       QuestionVoteResponseDto questionVoteResponseDto =
               questionMapper.QuestionVoteEntityToResponseDto(save);

       return new ResponseEntity<>(
               SingleResponseDto.of(questionVoteResponseDto), HttpStatus.CREATED);
    }

    @PatchMapping("/{questionVoteId}")
    private ResponseEntity QuestionVotepatch(@PathVariable Long questionVoteId,
                                             @RequestBody QuestionVotePatchDto questionVotePatchDto){
       QuestionVote questionVote  = questionMapper.QuestionVotePatchDtoToEntity(questionVotePatchDto);
       QuestionVote patch = questionVoteService.patch(questionVote,questionVoteId,questionVotePatchDto);
       QuestionVoteResponseDto questionVoteResponseDto = questionMapper.QuestionVoteEntityToResponseDto(patch);
        return new ResponseEntity<>(
                SingleResponseDto.of(questionVoteResponseDto), HttpStatus.CREATED);

    }





}
