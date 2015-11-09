package com.github.teamcalendar.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.github.teamcalendar.dao.QuestionDAO;
import com.github.teamcalendar.domain.PermissionEntity;
import com.github.teamcalendar.domain.QuestionEntity;
import com.github.teamcalendar.middleware.dto.Permission;
import com.github.teamcalendar.middleware.dto.Question;
import com.github.teamcalendar.middleware.utils.MapperService;

@Repository("questionDAO")
public class QuestionDAOImpl extends AbstractDao<Integer, QuestionEntity> implements QuestionDAO
{

    private static final Logger LOG = LoggerFactory.getLogger(QuestionEntity.class);

    @Override
    public void add(Question question)
    {
        QuestionEntity data = convertQuestionToEntity(question);
        persist(data);
    }

    @Override
    public void update(Question question)
    {
        QuestionEntity data = convertQuestionToEntity(question);
        update(data);
    }

    @Override
    public void delete(Question question)
    {
        QuestionEntity data = convertQuestionToEntity(question);
        delete(data);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Question> getAllQestions()
    {

        final List<Question> result = new ArrayList<Question>();

        Criteria criteria = createEntityCriteria();
        List<QuestionEntity> questions = (List<QuestionEntity>)criteria.list();

        if (CollectionUtils.isEmpty(questions))
        {
            LOG.error("NULL reference on users");
            return result;
        }

        for (QuestionEntity data : questions)
        {
            Question temp = convertEntityToQuestion(data);
            result.add(temp);
        }

        return result;
    }
    
    private Question convertEntityToQuestion(QuestionEntity entity)
    {
        return MapperService.getInstance().map(entity, Question.class);
    }

    private QuestionEntity convertQuestionToEntity(Question question)
    {
        return MapperService.getInstance().map(question, QuestionEntity.class);
    }

}
