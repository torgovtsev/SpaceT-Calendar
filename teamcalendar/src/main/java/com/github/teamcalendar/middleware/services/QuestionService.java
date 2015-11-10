package com.github.teamcalendar.middleware.services;

import java.util.List;

import com.github.teamcalendar.middleware.dto.Question;

public interface QuestionService
{

    void addQuestion(Question question);

    void updateQuestion(Question question);

    void deleteQuestion(Question question);

    List<Question> getAllQestions();
}
