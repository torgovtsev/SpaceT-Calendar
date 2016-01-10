package com.github.teamcalendar.frontend.component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.teamcalendar.middleware.dto.Event;
import com.github.teamcalendar.middleware.dto.EventType;
import com.github.teamcalendar.middleware.dto.Information;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.services.CalendarEventService;
import com.github.teamcalendar.middleware.services.EventTypeService;
import com.github.teamcalendar.middleware.services.UserService;
import com.github.teamcalendar.middleware.services.UsersService;


@ManagedBean(name="eventBean")
@Component(value="eventBean")
@ViewScoped
public class EventBean {
	private String user; // user name
	private String user1;
	private Date date2;
	private Date date1;
	

	private String message = "";
	private String eventType;
	private List<String> users = new ArrayList<String>(0);
	private List<String> eventTypes = new ArrayList<String>(0);
	
	@Autowired
	private EventTypeService eventTypeService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private CalendarEventService eventService;
	
	public List<String> getAllEventTypes() //get names
    {
		eventTypes.clear();
        try
        {
            List<EventType> types = eventTypeService.getAllEventTypes();
            for (EventType et : types) {
            	eventTypes.add(et.getName());
            }
            return eventTypes;
        }
        catch (Exception e)
        {
            return null;
        }
    }
	
	public List<String> getAllUsers() //get emails
    {
		users.clear();
        try
        {
            List<User> list = usersService.getAllUsers();
            for (User u : list) {
            	users.add(u.getEmail());
            }
            return users;
        }
        catch (Exception e)
        {
            return null;
        }
    }
	
	public void addEvent() {
		//System.out.println("ff");
		Event e = new Event();
		User u = usersService.getUserByEmail(user);
		e.setUser(u);
		Information inf = new Information();
		inf.setMessage(message);
		Integer id = eventService.AddInfo(inf);
		inf = eventService.getInfoById(id);
		//insert in db
		e.setInfo(inf);
		e.setDate(date1);
		EventType et = eventTypeService.getEventTypeByName(eventType);
		e.setEventType(et);
		//insert in db
		eventService.addEvent(e);
	}
	
	public void deleteEvent() {
		User u = usersService.getUserByEmail(user1);
		List<Date> dates = new ArrayList<Date>(); dates.add(date2);
		List<Event> e = eventService.getEventByUserDate(u.getId(), dates);
		if (e != null)
			eventService.deleteEvent(e.get(0));
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

	public List<String> getEventTypes() {
		return eventTypes;
	}

	public void setEventTypes(List<String> eventTypes) {
		this.eventTypes = eventTypes;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	
	public Date getDate1() {
		return date1;
	}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	} 
	
	public String getUser1() {
		return user1;
	}

	public void setUser1(String user1) {
		this.user1 = user1;
	} 
}
