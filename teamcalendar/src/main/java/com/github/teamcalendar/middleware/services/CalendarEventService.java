package com.github.teamcalendar.middleware.services;

import java.util.Date;
import java.util.List;

import com.github.teamcalendar.middleware.dto.Event;
import com.github.teamcalendar.middleware.dto.EventType;
import com.github.teamcalendar.middleware.dto.Information;

public interface CalendarEventService {
	void addEvent(Event event);
	void deleteEvent(Event event);
	void updateEvent(Event event);
	//get events by criteria
	List<Event> getAllEvents();
	
	List<Event> getEventsByUserId(Integer userId);
	
	List<Event> getEventsByTypeDate(EventType etype, Integer year, Integer month);
	
	List<Event> getEventByUserDate(Integer uid, List<Date> d);
	
	Information getInfoById(Integer id);
	
	Integer AddInfo(Information info);
}
