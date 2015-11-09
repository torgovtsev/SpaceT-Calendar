package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.middleware.dto.Question;

public interface QuestionDAO
{

    void add(Question question);

    void update(Question question);

    void delete(Question question);

    List<Question> getAllQestions();

}
