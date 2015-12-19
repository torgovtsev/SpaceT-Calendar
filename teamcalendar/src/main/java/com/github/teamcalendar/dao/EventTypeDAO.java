package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.EventEntity;
import com.github.teamcalendar.domain.EventTypeEntity;

public interface EventTypeDAO extends AbstractDao<String, EventTypeEntity> {
    void create(EventTypeEntity eventtype);

    void update(EventTypeEntity eventtype);

    void delete(EventTypeEntity eventtype);
	//get events by criteria
    List<EventTypeEntity> getAllEventTypes();
}
