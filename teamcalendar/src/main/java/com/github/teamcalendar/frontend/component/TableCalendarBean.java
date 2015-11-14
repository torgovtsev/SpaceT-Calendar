package com.github.teamcalendar.frontend.component;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.stereotype.Component;

@ManagedBean(name="tableCalendarBean")
@Component(value="tableCalendarBean")
@ViewScoped
public class TableCalendarBean {
	private String currentUser = "%";
	private String group = "All";
	private String region = "default";
	private String today = "All";
	private String startMonth = "August";
	private String startYear = "2015";
	private List<Integer> days;
	private List<String> weeks;
	public List<String> getWeeks() {
		return weeks;
	}

	public void setWeeks(List<String> weeks) {
		this.weeks = weeks;
	}

	private Integer daysCount = 7;
	
	
	public Integer getDaysCount() {
		return daysCount;
	}

	public void setDaysCount(Integer daysCount) {
		this.daysCount = daysCount;
	}

	@PostConstruct
    public void init() {
	
		days = new ArrayList<Integer>();
	    days.add(1);
	    days.add(2);
	    days.add(3);
	    days.add(4);
	    days.add(5);
	    days.add(6);
	    days.add(7);
	    weeks = new ArrayList<String>();
	    weeks.add("Week 1");
	}

	public List<Integer> getDays() {
		return days;
	}

	public void setDays(List<Integer> days) {
		this.days = days;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}
	
}
