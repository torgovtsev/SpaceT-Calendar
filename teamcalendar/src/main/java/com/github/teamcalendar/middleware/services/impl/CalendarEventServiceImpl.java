package com.github.teamcalendar.middleware.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.teamcalendar.dao.EventDAO;
import com.github.teamcalendar.domain.EventEntity;
import com.github.teamcalendar.domain.EventTypeEntity;
import com.github.teamcalendar.domain.UserEntity;
import com.github.teamcalendar.middleware.dto.Event;
import com.github.teamcalendar.middleware.dto.Event_Type;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.services.CalendarEventService;
import com.github.teamcalendar.middleware.utils.MapperService;

@Service("eventService")
@Transactional
public class CalendarEventServiceImpl implements CalendarEventService {
	
	@Autowired
	private EventDAO dao;

	@Override
	public void addEvent(Event event) {
		EventEntity eventEntity = convertEventToEntity(event);
		dao.create(eventEntity);
	}

	@Override
	public void deleteEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Event> getAllEvents() {
		final List<Event> result = new ArrayList<Event>();

        List<EventEntity> evtsEntity = dao.getAllEvents();

        for (EventEntity data : evtsEntity)
        {
            Event et= convertEntityToEvent(data);
            result.add(et);
        }
        return result;
	}
	
	
	public List<Event> getEventsByUserId(Integer userId) {
		List<Event> result = new ArrayList<Event>();
		List<EventEntity> eventEntities = dao.getEventsByUser(userId);
		for (EventEntity entity : eventEntities) {
			Event event = convertEntityToEvent(entity);
			result.add(event);
		}
		return result;        
	}
	
	private Event convertEntityToEvent(EventEntity entity)
    {
        return MapperService.getInstance().map(entity, Event.class);
    }
	
    private EventEntity convertEventToEntity(Event event)
    {
        return MapperService.getInstance().map(event, EventEntity.class);
    }

}
