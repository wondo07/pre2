package com.example.preprojec3.question.repository;

import com.example.preprojec3.question.entity.Question;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}
