package com.github.teamcalendar.middleware.services;

import java.util.List;

import com.github.teamcalendar.middleware.dto.EventType;
import com.github.teamcalendar.middleware.services.impl.EventTypeServiceImpl;

/**
 * @author Artem Ivanov
 * @see {@link EventTypeServiceImpl}
 */
public interface EventTypeService
{

    boolean addEventType(EventType eventType);

    boolean updateEventType(EventType eventType);

    boolean deleteEventType(EventType eventType);

    List<EventType> getAllEventTypes();

    EventType getEventTypeById(Integer id);

}
