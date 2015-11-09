package com.github.teamcalendar.middleware.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.teamcalendar.dao.QuestionDAO;
import com.github.teamcalendar.middleware.dto.Question;
import com.github.teamcalendar.middleware.services.QuestionService;

@Service("questionService")
@Transactional
public class QuestionServiceImpl implements QuestionService
{

    @Autowired
    QuestionDAO dao;

    public void add(Question question)
    {
        dao.add(question);
    }

    public void update(Question question)
    {
        dao.update(question);
    }

    public void delete(Question question)
    {
        dao.delete(question);
    }

    public List<Question> getAllQestions()
    {
        return dao.getAllQestions();
    }

}
