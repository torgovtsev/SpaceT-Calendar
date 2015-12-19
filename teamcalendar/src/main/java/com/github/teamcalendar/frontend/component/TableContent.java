package com.github.teamcalendar.frontend.component;

import java.util.List;
import java.util.Map;

import com.github.teamcalendar.middleware.dto.Event;
import com.github.teamcalendar.middleware.dto.User;

public class TableContent {
	
	public String currentMonth;
	public Integer curMonthInt;
	public List<Integer> days;
	public Integer daysCount = 30;
	public List<Map.Entry<Integer, Integer>> weeks;
	public List<String> weekDays;
	public String currentYear;
	
	public String getEvent(User user, Integer dayy) {
//		String date = dayy.toString() + "." + curMonthInt.toString() + "." + currentYear;
//		String res = "";
//		for (Event event : user.getUserEvent()) {
//			if (event.getCreated_on().equals(date))
//				return  res + event.getEventType().getName().charAt(0); 
//		}
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
