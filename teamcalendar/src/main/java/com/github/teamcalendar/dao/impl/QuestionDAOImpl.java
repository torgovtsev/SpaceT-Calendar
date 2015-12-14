package com.github.teamcalendar.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.github.teamcalendar.dao.QuestionDAO;
import com.github.teamcalendar.domain.QuestionEntity;

@Repository("questionDAO")
public class QuestionDAOImpl extends AbstractDaoImpl<Integer, QuestionEntity> implements QuestionDAO
{

    @SuppressWarnings("unchecked")
    public List<QuestionEntity> getAllQestions()
    {

        Criteria criteria = getCriteria();
        List<QuestionEntity> questions = (List<QuestionEntity>)criteria.list();

        return questions;
    }
}
