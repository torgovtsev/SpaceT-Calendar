package com.github.teamcalendar.frontend.component;

public class CustomDate {
	public String year;
	public Integer month;
	
	public CustomDate(String y, Integer m) {
		this.year = y;
		this.month = m;
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
}
