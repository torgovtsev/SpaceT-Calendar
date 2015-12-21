package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.EventEntity;

public interface EventDAO extends AbstractDao<Integer, EventEntity>
{

    //get events by criteria
    List<EventEntity> getAllEvents();

    List<EventEntity> getEventsByUser(Integer user_id);
}