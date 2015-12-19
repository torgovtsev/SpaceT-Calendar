package com.github.teamcalendar.middleware.services;

import java.util.List;

import com.github.teamcalendar.middleware.dto.Event;

public interface CalendarEventService {
	void addEvent(Event event);
	void deleteEvent(Event event);
	void updateEvent(Event event);
	//get events by criteria
	List<Event> getAllEvents();
	
	List<Event> getEventsByUserId(Integer userId);
}
