package com.example.preprojec3.question.repository;

import com.example.preprojec3.question.entity.QuestionVote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionVoteRepository extends JpaRepository<QuestionVote,Long> {
}
