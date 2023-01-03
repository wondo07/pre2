package com.example.preprojec3.question.controller;


import com.example.preprojec3.dto.PageResponseDto;
import com.example.preprojec3.dto.SingleResponseDto;
import com.example.preprojec3.question.dto.QuestionPatchDto;
import com.example.preprojec3.question.dto.QuestionPostDto;
import com.example.preprojec3.question.dto.QuestionResponseDto;
import com.example.preprojec3.question.entity.Question;
import com.example.preprojec3.question.mapper.QuestionMapper;
import com.example.preprojec3.question.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionMapper questionMapper;
    private final QuestionService questionService;

    // 질문 생성
    @PostMapping
    public ResponseEntity questionPost(@RequestBody QuestionPostDto questionPostDto){
       Question question = questionMapper.QuestionPostDtotoEntity(questionPostDto);
       Question save = questionService.save(question,questionPostDto.getUserId());
       QuestionResponseDto questionResponseDto = questionMapper.QuestionEntityToReponseDto(save);

       return new ResponseEntity<>(
               SingleResponseDto.of(questionResponseDto), HttpStatus.CREATED);
    }

    // 질문 단건 조회
    @GetMapping("/{questionId}")
    public ResponseEntity questionGet(@PathVariable Long questionId){
        Question question = questionService.get(questionId);
        QuestionResponseDto questionResponseDto = questionMapper.QuestionEntityToReponseDto(question);
        return new ResponseEntity<>(
                SingleResponseDto.of(questionResponseDto),HttpStatus.OK);
    }

    // 질문 전체 조회
    @GetMapping
    public ResponseEntity questionGets(
            @PageableDefault(page=0,size=10,sort="questionId",direction = Sort.Direction.DESC)
            Pageable pageable
    ){
        Page<Question> findQuestion = questionService.findAll(pageable);
        List<QuestionResponseDto> questionPageReponseDtoList =
                questionMapper.QuestionPageReponseDtoList(findQuestion.getContent());

        PageResponseDto pageResponseDto = PageResponseDto.of(
                questionPageReponseDtoList,
                new PageImpl(
                        questionPageReponseDtoList,
                        findQuestion.getPageable(),
                        findQuestion.getTotalElements()
                )
        );
        return new ResponseEntity<>(pageResponseDto,HttpStatus.OK);
    }

    // 질문 수정
    @PatchMapping("/{questionId}")
    public ResponseEntity questionPatch(@PathVariable Long questionId,
                                        @RequestBody QuestionPatchDto questionPatchDto){
        Question question = questionMapper.QuestionPatchDtoToEntity(questionPatchDto);
        Question patch = questionService.patch(question,questionId,questionPatchDto.getUserId());
        QuestionResponseDto questionResponseDto = questionMapper.QuestionEntityToReponseDto(patch);
        return new ResponseEntity<>(
                SingleResponseDto.of(questionResponseDto),HttpStatus.OK);
    }

    // 질문 단건 삭제
    @DeleteMapping("/{questionId}")
    public ResponseEntity questionDelete(@PathVariable Long questionId){
          questionService.delete(questionId);
          return ResponseEntity.noContent().build();
    }


    // 질문 전체 삭제
    @DeleteMapping
    public ResponseEntity questionDeletes(){
        questionService.deleteAll();
        return ResponseEntity.noContent().build();
    }


}
