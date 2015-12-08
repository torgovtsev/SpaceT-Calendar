package com.github.teamcalendar.middleware.services;

import java.util.List;

import com.github.teamcalendar.middleware.dto.Question;
import com.github.teamcalendar.middleware.services.impl.QuestionServiceImpl;

/**
 * @author Artem Ivanov
 * @see {@link QuestionServiceImpl}
 */
public interface QuestionService
{

    boolean addQuestion(Question question);

    boolean updateQuestion(Question question);

    boolean deleteQuestion(Question question);

    List<Question> getAllQestions();
}
