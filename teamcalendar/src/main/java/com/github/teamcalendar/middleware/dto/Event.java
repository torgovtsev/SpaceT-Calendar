package com.github.teamcalendar.middleware.dto;

import java.util.Date;

public class Event {
	private Integer id;
	private Event_Type eventType;
	private User user;
	private Date date;
	private Information info;

	
	public Event() {
		
	}
	
	public Event(Event_Type etype, User u, Date d, Information info) {
		this.eventType = etype;
		this.user = u;
		this.date = d;
		this.info = info;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public Event_Type getEventType() {
		return eventType;
	}
	public void setEventType(Event_Type eventType) {
		this.eventType = eventType;
	}
	public Information getInfo() {
		return info;
	}
	public void setInfo(Information info) {
		this.info = info;
	}

}
