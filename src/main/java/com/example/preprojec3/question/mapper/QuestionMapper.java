package com.example.preprojec3.question.mapper;


import com.example.preprojec3.question.dto.*;
import com.example.preprojec3.question.entity.Question;
import com.example.preprojec3.question.entity.QuestionComment;
import com.example.preprojec3.question.entity.QuestionVote;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {


    Question QuestionPostDtotoEntity(QuestionPostDto questionPostDto);

    QuestionResponseDto QuestionEntityToReponseDto(Question question);

    List<QuestionResponseDto> QuestionPageReponseDtoList(List<Question> content);

    Question QuestionPatchDtoToEntity(QuestionPatchDto questionPatchDto);

    QuestionComment QuestionCommentPostDtoToEntity(QuestionCommentPostDto questionCommentPostDto);

    QuestionCommentResponseDto QuestionCommentEntityToReponseDto(QuestionComment questionComment);

    QuestionComment QuestionCommentPatchDtoToEntity(QuestionCommentPatchDto questionCommentPatchDto);

    QuestionVote QuestionVotePostDtoToEntity(QuestionVotePostDto questionVotePostDto);

    QuestionVoteResponseDto QuestionVoteEntityToResponseDto(QuestionVote questionVote);

    QuestionVote QuestionVotePatchDtoToEntity(QuestionVotePatchDto questionVotePatchDto);
}
