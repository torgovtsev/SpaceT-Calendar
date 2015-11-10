package com.github.teamcalendar.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.github.teamcalendar.dao.QuestionDAO;
import com.github.teamcalendar.domain.QuestionEntity;
import com.github.teamcalendar.middleware.dto.Permission;
import com.github.teamcalendar.middleware.dto.Question;
import com.github.teamcalendar.middleware.utils.MapperService;

@Repository("questionDAO")
public class QuestionDAOImpl extends AbstractDao<Integer, QuestionEntity> implements QuestionDAO
{

    public void addQuestion(QuestionEntity question)
    {
        persist(question);
    }

    public void updateQuestion(QuestionEntity question)
    {
        update(question);
    }

    public void deleteQuestion(QuestionEntity question)
    {
        delete(question);
    }

    @SuppressWarnings("unchecked")
    public List<QuestionEntity> getAllQestions()
    {

        Criteria criteria = createEntityCriteria();
        List<QuestionEntity> questions = (List<QuestionEntity>)criteria.list();

        return questions;
    }
}
