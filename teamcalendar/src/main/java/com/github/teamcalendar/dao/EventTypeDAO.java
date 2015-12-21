package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.EventTypeEntity;

public interface EventTypeDAO extends AbstractDao<Integer, EventTypeEntity>
{

    //get events by criteria
    List<EventTypeEntity> getAllEventTypes();
}
