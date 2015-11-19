package com.github.teamcalendar.frontend.component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

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
	private String month;
	private String currentMonth;
	private List<String> months;
	private String startYear = "2015";
	private List<Integer> days;
	private List<Map.Entry<Integer, Integer>> weeks;
	private List<Integer> weekEntries;


	private Calendar calendar;
	private Integer daysCount = 30;
	


	@PostConstruct
    public void init() throws ParseException {
		calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"));
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		days = new ArrayList<Integer>();
		int cnt = 1;
		while (cnt < 31) {
			days.add(cnt);
			cnt++;
		}
	    //weeks = new ArrayList<String>();
	   // weeks.add("Week 1");
	    months = new ArrayList<String>();
	    months.add("January");
	    months.add("February");
	    months.add("March");
	    months.add("April");
	    months.add("May");
	    months.add("June");
	    months.add("July");
	    months.add("August");
	    months.add("September");
	    months.add("October");
	    months.add("November");
	    months.add("December");	 
	    currentMonth = months.get(calendar.getTime().getMonth());
	    weeks = new ArrayList<Map.Entry<Integer, Integer>>();
	    weekEntries = new ArrayList<Integer>();
	    setWeeksMethod();
	}
	
	private void setWeeksMethod() throws ParseException {
		int week = 0; Integer prevWeek = 0; Integer cnt = 1; int prevCnt = 0; int curWeek = 0; int u = 0;
		for (Integer day : days) {
			//temp plug
			String input = "2015" + "11";
			input += day.toString().length() == 1 ? "0" : "";
			input += day.toString();
			String format = "yyyyMMdd";
			SimpleDateFormat df = new SimpleDateFormat(format);
			Date date = df.parse(input);			
			calendar.setTime(date);
			prevWeek = week;
			week = calendar.get(Calendar.WEEK_OF_YEAR);
			u = calendar.get(Calendar.DAY_OF_WEEK);
			if (prevWeek != 0)
				if (week == prevWeek) {
					cnt++;
				} else {
					prevCnt = cnt;
					cnt = 1;
					weeks.add(new AbstractMap.SimpleEntry<Integer, Integer>(prevWeek, prevCnt));
					//curWeek = prevWeek;
					//weeks.add(prevWeek); weekEntries.add(prevCnt);
				}
		}
		Map.Entry<Integer, Integer> entry = weeks.get(weeks.size()-1);
		if (entry.getKey() == week)
			entry.setValue(prevCnt + 1);
		else
			weeks.add(new AbstractMap.SimpleEntry<Integer, Integer>(week, 1));
	}


	/*private void setMonthName() {
		int numMonth = calendar.getTime().getMonth();
		switch (numMonth) {
		   case 1:  month = "January";
					break;
		   case 2:  month = "February";
		            break;
		   case 3:  month = "March";
		            break;
		   case 4:  month = "April";
		            break;
		   case 5:  month = "May";
		            break;
		   case 6:  month = "June";
		            break;
		   case 7:  month = "July";
		            break;
		   case 8:  month = "August";
		            break;
		   case 9:  month = "September";
		            break;
		   case 10: month = "October";
		            break;
		   case 11: month = "November";
		            break;
		   case 12: month = "December";
		            break;
		}
	}*/
	
	public List<Integer> getWeekEntries() {
		return weekEntries;
	}

	public void setWeekEntries(List<Integer> weekEntries) {
		this.weekEntries = weekEntries;
	}
	
	public String getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(String currentMonth) {
		this.currentMonth = currentMonth;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
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
	
	
	public List<String> getMonths() {
		return months;
	}

	public void setMonths(List<String> months) {
		this.months = months;
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
