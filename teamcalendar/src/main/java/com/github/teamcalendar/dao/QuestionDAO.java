package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.QuestionEntity;

public interface QuestionDAO extends AbstractDao<Integer, QuestionEntity>
{

    void create(QuestionEntity question);

    void update(QuestionEntity question);

    void delete(QuestionEntity question);

    List<QuestionEntity> getAllQestions();

}
