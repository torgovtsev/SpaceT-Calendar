package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.QuestionEntity;
import com.github.teamcalendar.middleware.dto.Question;

public interface QuestionDAO
{

    void addQuestion(QuestionEntity question);

    void updateQuestion(QuestionEntity question);

    void deleteQuestion(QuestionEntity question);

    List<QuestionEntity> getAllQestions();

}
