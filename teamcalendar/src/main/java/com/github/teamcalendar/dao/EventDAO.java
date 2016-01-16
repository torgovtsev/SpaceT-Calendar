package com.github.teamcalendar.dao;

import java.util.Date;
import java.util.List;

import com.github.teamcalendar.domain.EventEntity;
import com.github.teamcalendar.middleware.dto.Event;
import com.github.teamcalendar.middleware.dto.EventType;

public interface EventDAO extends AbstractDao<Integer, EventEntity>
{

    //get events by criteria
    List<EventEntity> getAllEvents();

    List<EventEntity> getEventsByUser(Integer user_id);
    
    List<EventEntity> getEventsByTypeDate(EventType etype, Integer year, Integer month);
    
    List<EventEntity> getEventByUserDate(Integer uid, List<Date> d);
}
