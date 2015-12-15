package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.QuestionEntity;

public interface QuestionDAO extends AbstractDao<Integer, QuestionEntity>
{

    List<QuestionEntity> getAllQestions();

}
