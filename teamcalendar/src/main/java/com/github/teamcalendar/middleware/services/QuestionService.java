package com.github.teamcalendar.middleware.services;

import java.util.List;

import com.github.teamcalendar.middleware.dto.Question;

public interface QuestionService
{

    void add(Question question);

    void update(Question question);

    void delete(Question question);

    List<Question> getAllQestions();
}
