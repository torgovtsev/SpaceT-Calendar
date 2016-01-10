package com.github.teamcalendar.frontend.component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.teamcalendar.middleware.dto.Event;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.services.CalendarEventService;

public class TableContent {
	
	public String currentMonth;
	public Integer curMonthInt;
	public List<Integer> days;
	public Integer daysCount = 30;
	public List<Map.Entry<Integer, Integer>> weeks;
	public List<String> weekDays;
	public String currentYear;
	public String color;
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public CalendarEventService eventService;
	
	private List<Event> userEvents = new ArrayList<Event>();
		
	
	public String getSymbol(Event event, Integer dayy) {
		if (event == null) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.YEAR, Integer.parseInt(currentYear));
			calendar.set(Calendar.MONTH, curMonthInt-1);
			calendar.set(Calendar.DAY_OF_MONTH, dayy);
			int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);
			return (dayWeek == 1 || dayWeek == 7) ? "" : "8";
		}
		else {
			String res = "";
			
			return res = res + event.getEventType().getName().charAt(0);
		}
	}

	public Event getEvent(User user, Integer dayy) {
//		String date = dayy.toString() + "." + curMonthInt.toString() + "." + currentYear;
		String res = "";
		String res1 = "";
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(currentYear));
		calendar.set(Calendar.MONTH, curMonthInt-1);
		calendar.set(Calendar.DAY_OF_MONTH, dayy);
		int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);
		
		for (Event e : userEvents) {
			if (e.getDate().equals(d))
				return e;
		}
		//Date d = calendar.getTime();

		//userEvent = eventService.getEventByUserDate(user.getId(), d);
		///if (userEvent != null)
			///return userEvent;
		
		/*for (Event event : userEvents) {
			Date d = event.getDate();
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			int year = cal.get(Calendar.YEAR);
		    int month = cal.get(Calendar.MONTH);
		    int day = cal.get(Calendar.DAY_OF_MONTH);
			//res1 = (dayWeek == 1 || dayWeek == 7) ? "" : "8";
			if (day == dayy && month+1 == curMonthInt && year == Integer.parseInt(currentYear)) {
				return event;
			}
		}*/
		
		//return (dayWeek == 1 || dayWeek == 7) ? "" : "8";
		return null;
	}
	
	private Date d;
	public String getBackColor(User user, Integer dayy) {
		//if dayy == 1 then update the list
		String res = "";
		String res1 = "";
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(currentYear));
		calendar.set(Calendar.MONTH, curMonthInt-1);
		calendar.set(Calendar.DAY_OF_MONTH, dayy);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);  
	    calendar.set(Calendar.SECOND, 0);  
	    calendar.set(Calendar.MILLISECOND, 0);
		int dayWeek = calendar.get(Calendar.DAY_OF_WEEK); 
		d = calendar.getTime();
		if (dayy == 1) {
			userEvents.clear();
			List<Date> dates = new ArrayList<Date>();
			dates.add(d); 
			int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
			calendar.set(Calendar.DAY_OF_MONTH, daysInMonth);
			Date d1 = calendar.getTime();
			dates.add(d1);
			userEvents = eventService.getEventByUserDate(user.getId(), dates);
		}
		for (Event e : userEvents) {
			if (e.getDate().equals(d))
				return res = res + e.getEventType().getBackColor();
		}
		//userEvent = eventService.getEventByUserDate(user.getId(), d);
		//if (userEvent != null)
			//return res = res + userEvent.getEventType().getBackColor();

		return (dayWeek == 1 || dayWeek == 7) ? "ffff00" : "";
	}
	
	public String getForeColor(User user, Integer dayy) {
//		String date = dayy.toString() + "." + curMonthInt.toString() + "." + currentYear;
		String res = "";
		String res1 = "";
		//if (userEvents.size() > 0 && userEvents.get(0).getUser().getId() != user.getId())
			
		for (Event e : userEvents) {
			if (e.getDate().equals(d))
				return res = res + e.getEventType().getTextColor();
		}
		//Date d = calendar.getTime();

		//userEvent = eventService.getEventByUserDate(user.getId(), d);
		///if (userEvent != null)
		///	return res = res + userEvent.getEventType().getTextColor();
		/*for (Event event : userEvents) {
			Date d = event.getDate();
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			int year = cal.get(Calendar.YEAR);
		    int month = cal.get(Calendar.MONTH);
		    int day = cal.get(Calendar.DAY_OF_MONTH);
		    int dayWeek = cal.get(Calendar.DAY_OF_WEEK); //compare day. for it make calendar.set(date dayy ...)
			//res1 = (dayWeek == 1 || dayWeek == 7) ? "" : "8";
			if (day == dayy && month+1 == curMonthInt && year == Integer.parseInt(currentYear)) {
				return res = res + event.getEventType().getTextColor();
			}
		}*/
		return "";
	}
	
	public String getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(String currentYear) {
		this.currentYear = currentYear;
	}

	public List<String> getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(List<String> weekDays) {
		this.weekDays = weekDays;
	}
	
	public List<Map.Entry<Integer, Integer>> getWeeks() {
		return weeks;
	}

	public void setWeeks(List<Map.Entry<Integer, Integer>> weeks) {
		this.weeks = weeks;
	}
	
	public Integer getDaysCount() {
		return daysCount;
	}

	public void setDaysCount(Integer daysCount) {
		this.daysCount = daysCount;
	}
	
	
	public List<Integer> getDays() {
		return days;
	}

	public void setDays(List<Integer> days) {
		this.days = days;
	}
	

	
	public String getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(String currentMonth) {
		this.currentMonth = currentMonth;
	}
}
