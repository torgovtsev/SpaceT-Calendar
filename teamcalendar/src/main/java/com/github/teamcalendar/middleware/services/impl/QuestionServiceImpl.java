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

/**
 * @author Artem Ivanov
 *
 */
@Service("questionService")
@Transactional
public class QuestionServiceImpl implements QuestionService
{

    private static final Logger LOG = LoggerFactory.getLogger(QuestionEntity.class);

    @Autowired
    QuestionDAO                 dao;

    /**
     * @param question
     *            if a null object parameter is passed to method, nothing will happen
     * @return true in case of operation success
     */
    public boolean addQuestion(Question question)
    {
        if (question == null)
        {
            return false;
        }

        try
        {
            QuestionEntity questionEntity = convertQuestionToEntity(question);
            dao.create(questionEntity);
            return true;
        }
        catch (Exception e)
        {
            LOG.error("Failed to add question");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param question
     *            if a null object parameter is passed to method, nothing will happen
     * @return true in case of operation success
     */
    public boolean updateQuestion(Question question)
    {
        if (question == null)
        {
            return false;
        }
        try
        {
            QuestionEntity questionEntity = convertQuestionToEntity(question);
            dao.update(questionEntity);
            return true;
        }
        catch (Exception e)
        {
            LOG.error("Failed to update question");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param question
     *            if a null object parameter is passed to method, nothing will happen
     * @return true in case of operation success
     */
    public boolean deleteQuestion(Question question)
    {
        if (question == null)
        {
            return false;
        }
        try
        {
            QuestionEntity questionEntity = convertQuestionToEntity(question);
            dao.delete(questionEntity);
            return true;
        }
        catch (Exception e)
        {
            LOG.error("Failed to delete question");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @return <code>null</code> if there is no questions in table
     */
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
