package com.example.preprojec3.question.repository;

import com.example.preprojec3.question.entity.QuestionComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionCommentRepository extends JpaRepository<QuestionComment,Long> {
}
