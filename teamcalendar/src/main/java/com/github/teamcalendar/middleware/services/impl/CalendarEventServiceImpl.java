package com.github.teamcalendar.middleware.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.teamcalendar.dao.EventDAO;
import com.github.teamcalendar.dao.InformationDAO;
import com.github.teamcalendar.domain.EventEntity;
import com.github.teamcalendar.domain.InformationEntity;
import com.github.teamcalendar.middleware.dto.Event;
import com.github.teamcalendar.middleware.dto.EventType;
import com.github.teamcalendar.middleware.dto.Information;
import com.github.teamcalendar.middleware.services.CalendarEventService;
import com.github.teamcalendar.middleware.utils.MapperService;

@Service("eventService")
@Transactional
public class CalendarEventServiceImpl implements CalendarEventService
{

    @Autowired
    private EventDAO dao;
    
    @Autowired
    private InformationDAO infDao;

    @Override
    public void addEvent(Event event)
    {
        EventEntity eventEntity = convertEventToEntity(event);
        dao.create(eventEntity);
    }
    
    @Override
    public List<Event> getEventByUserDate(Integer uid, List<Date> d) {
        List<EventEntity> e = dao.getEventByUserDate(uid, d);
    	
    	if (e != null) {
    		List<Event> es = new ArrayList<Event>(0);
    		for (EventEntity ent : e) {
    			es.add(convertEntityToEvent(ent));
    		}
    		return es;
    	}
    	return null;
    }

    @Override
    public void deleteEvent(Event event)
    {
    	EventEntity eventEntity = convertEventToEntity(event);
    	dao.delete(eventEntity);
    }

    @Override
    public void updateEvent(Event event)
    {
        EventEntity eventEntity = convertEventToEntity(event);
        dao.update(eventEntity);
    }

    @Override
    public List<Event> getAllEvents()
    {
        final List<Event> result = new ArrayList<Event>();

        List<EventEntity> evtsEntity = dao.getAllEvents();

        for (EventEntity data : evtsEntity)
        {
            Event et = convertEntityToEvent(data);
            result.add(et);
        }
        return result;
    }

    public List<Event> getEventsByUserId(Integer userId)
    {
        List<Event> result = new ArrayList<Event>();
        List<EventEntity> eventEntities = dao.getEventsByUser(userId);
        for (EventEntity entity : eventEntities)
        {
            Event event = convertEntityToEvent(entity);
            result.add(event);
        }
        return result;
    }

    private Event convertEntityToEvent(EventEntity entity)
    {
        return MapperService.getInstance().map(entity, Event.class);
    }
    
    private Information convertEntityToInformation(InformationEntity entity)
    {
        return MapperService.getInstance().map(entity, Information.class);
    }

    private EventEntity convertEventToEntity(Event event)
    {
        return MapperService.getInstance().map(event, EventEntity.class);
    }
    
    private InformationEntity convertInformationToEntity(Information info)
    {
        return MapperService.getInstance().map(info, InformationEntity.class);
    }

	public Information getInfoById(Integer id) {
		InformationEntity ie = infDao.getById(id);
        return convertEntityToInformation(ie);
	}

	@Override
	public Integer AddInfo(Information info) {
		InformationEntity ient = convertInformationToEntity(info);
    	infDao.create(ient);
    	return ient.getId();
	}

	@Override
	public List<Event> getEventsByTypeDate(EventType etype, Integer year,
			Integer month) {
		List<Event> result = new ArrayList<Event>();
        List<EventEntity> eventEntities = dao.getEventsByTypeDate(etype, year, month);
        for (EventEntity entity : eventEntities)
        {
            Event event = convertEntityToEvent(entity);
            result.add(event);
        }
        return result;
	}
}
