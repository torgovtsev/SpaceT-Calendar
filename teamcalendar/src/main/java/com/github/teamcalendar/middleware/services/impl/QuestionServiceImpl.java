package com.github.teamcalendar.middleware.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.teamcalendar.dao.QuestionDAO;
import com.github.teamcalendar.domain.QuestionEntity;
import com.github.teamcalendar.middleware.dto.Question;
import com.github.teamcalendar.middleware.services.QuestionService;
import com.github.teamcalendar.middleware.utils.MapperService;

@Service("questionService")
@Transactional
public class QuestionServiceImpl implements QuestionService
{

    private static final Logger LOG = LoggerFactory.getLogger(QuestionEntity.class);

    @Autowired
    QuestionDAO                 dao;

    public void addQuestion(Question question)
    {
        if (question == null)
        {
            return;
        }
        QuestionEntity questionEntity = convertQuestionToEntity(question);
        dao.addQuestion(questionEntity);
    }

    public void updateQuestion(Question question)
    {
        if (question == null)
        {
            return;
        }
        QuestionEntity questionEntity = convertQuestionToEntity(question);
        dao.updateQuestion(questionEntity);
    }

    public void deleteQuestion(Question question)
    {
        if (question == null)
        {
            return;
        }
        QuestionEntity questionEntity = convertQuestionToEntity(question);
        dao.deleteQuestion(questionEntity);
    }

    public List<Question> getAllQestions()
    {

        final List<Question> result = new ArrayList<Question>();

        List<QuestionEntity> questionsEntity = dao.getAllQestions();

        if (CollectionUtils.isEmpty(questionsEntity))
        {
            LOG.error("NULL reference on questions");
            return result;
        }

        for (QuestionEntity data : questionsEntity)
        {
            Question questions = convertEntityToQuestion(data);
            result.add(questions);
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
