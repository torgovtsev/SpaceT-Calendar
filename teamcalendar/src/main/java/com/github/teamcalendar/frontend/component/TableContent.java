package com.github.teamcalendar.frontend.component;

import java.util.List;
import java.util.Map;

public class TableContent {
	
	public String currentMonth;
	public List<Integer> days;
	public Integer daysCount = 30;
	public List<Map.Entry<Integer, Integer>> weeks;
	public List<String> weekDays;
	
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
