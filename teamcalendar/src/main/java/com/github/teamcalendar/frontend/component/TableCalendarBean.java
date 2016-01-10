package com.github.teamcalendar.frontend.component;

import java.io.Serializable;
import java.text.DateFormatSymbols;
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
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.teamcalendar.middleware.dto.Event;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.services.CalendarEventService;
import com.github.teamcalendar.middleware.services.UsersService;

@ManagedBean(name="tableCalendarBean")
@Component(value="tableCalendarBean")
@RequestScoped
public class TableCalendarBean {	
//
	private String currentUser = "%";
	private String group = "All";
	private String region = "default";
	private String today = "All";
	private String month = "Show 1 month";
	
	
	private String startYear = "2016";
	private List<TableContent> tables;
	private String onApply;
	private List<String> countMonths;
	public List<String> currentMonths;
	

	public List<String> getCountMonths() {
		return countMonths;
	}

	public void setCountMonths(List<String> countMonths) {
		this.countMonths = countMonths;
	}

	public List<String> getCurrentMonths() {
		return currentMonths;
	}

	public void setCurrentMonths(List<String> currentMonths) {
		this.currentMonths = currentMonths;
	}


	public String getOnApply() {
		return onApply;
	}

	public void setOnApply(String onApply) {
		this.onApply = onApply;
	}

	public List<TableContent> getTables() {
		return tables;
	}

	public void setTables(List<TableContent> tables) {
		this.tables = tables;
	}

			
	private Calendar calendar;
	
	@Autowired
	private CalendarEventService eventService;
	
	//temp
	private List<Event> events;
	
    @Autowired
    private UsersService userService;
	private List<User> users;


	@PostConstruct
     public void init() throws ParseException {
		calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"));
		tables = new ArrayList<TableContent>();
	    tables.add(new TableContent());
	    currentMonths = new ArrayList<String>();	    
	    currentMonths.add("January");
	    currentMonths.add("February");
	    currentMonths.add("March");
	    currentMonths.add("April");
	    currentMonths.add("May");
	    currentMonths.add("June");
	    currentMonths.add("July");
	    currentMonths.add("August");
	    currentMonths.add("September");
	    currentMonths.add("October");
	    currentMonths.add("November");
	    currentMonths.add("December");
	    tables.get(0).eventService = eventService;
		tables.get(0).currentMonth = currentMonths.get(calendar.getTime().getMonth());
		tables.get(0).curMonthInt = MonthToInt(tables.get(0).currentMonth);
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		tables.get(0).days = new ArrayList<Integer>();
		int iDay = 1;
		Calendar c = new GregorianCalendar(Integer.parseInt(startYear), MonthToInt(tables.get(0).currentMonth)-1, iDay);
		tables.get(0).daysCount = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		int cnt = 1;				
		while (cnt <= tables.get(0).daysCount) {
			tables.get(0).days.add(cnt);
			cnt++;
		}
	 	    
		tables.get(0).weeks = new ArrayList<Map.Entry<Integer, Integer>>();
		CustomDate cd = new CustomDate(startYear, MonthToInt(tables.get(0).currentMonth));
	    setWeeksMethod(tables.get(0).weeks, cd, tables.get(0).days);
	    tables.get(0).weekDays = new ArrayList<String>();
	    tables.get(0).currentYear = startYear;
	    setWeekDaysMethod(tables.get(0).weekDays, cd, tables.get(0).days);
	    users = userService.getAllUsers();
	    
	    countMonths = new ArrayList<String>();
	    countMonths.add("Show 1 month"); 
	    countMonths.add("Show 2 months"); 
	    countMonths.add("Show 3 months"); 
	    
	}
	
	
	private void setWeekDaysMethod(List<String> weeks, CustomDate cd, List<Integer> days) throws ParseException{
		for (Integer day : days) {
			String input = cd.year;
			input += cd.month.toString().length() == 1 ? "0" : "";
			input += cd.month.toString();
			input += day.toString().length() == 1 ? "0" : "";
			input += day.toString();
			String format = "yyyyMMdd";
			SimpleDateFormat df = new SimpleDateFormat(format);
			Date date = df.parse(input);					
			calendar.setTime(date);
			int weekDay = calendar.get(Calendar.DAY_OF_WEEK);			
			
			switch (weekDay) {
			case 1:
				weeks.add("Su");
				break;
			case 2:
				weeks.add("Mo");
				break;
			case 3:
				weeks.add("Tu");
				break;
			case 4:
				weeks.add("We");
				break;
			case 5:
				weeks.add("Th");
				break;
			case 6:
				weeks.add("Fr");
				break;
			case 7:
				weeks.add("Sa");
				break;
			}
		}
	}

	private void setWeeksMethod(List<Map.Entry<Integer, Integer>> weeks, CustomDate cd, List<Integer> days) throws ParseException {
		int week = 0; Integer prevWeek = 0; Integer cnt = 1; int prevCnt = 0; int curWeek = 0; int u = 0;
		for (Integer day : days) {
			
			String input = cd.year;
			input += cd.month.toString().length() == 1 ? "0" : "";
			input += cd.month.toString();
			input += day.toString().length() == 1 ? "0" : "";
			input += day.toString();
			String format = "yyyyMMdd";
			SimpleDateFormat df = new SimpleDateFormat(format);
			Date date = df.parse(input);			
			calendar.setTime(date);
			prevWeek = week;
			week = calendar.get(Calendar.WEEK_OF_YEAR);
			//u = calendar.get(Calendar.DAY_OF_WEEK);
			if (prevWeek != 0)
				if (week == prevWeek) {
					cnt++;
				} else {
					prevCnt = cnt;
					cnt = 1;
					weeks.add(new AbstractMap.SimpleEntry<Integer, Integer>(prevWeek, prevCnt));
				}
		}
		Map.Entry<Integer, Integer> entry = weeks.get(weeks.size()-1);
		if (entry.getKey() == week)
			entry.setValue(prevCnt + 1);
		else
			weeks.add(new AbstractMap.SimpleEntry<Integer, Integer>(week, cnt));
	}
	
	private Integer MonthToInt(String m) {
		switch (m) {
		case "January":
			return 1;
		case "February":
			return 2;
		case "March":
			return 3;
		case "April":
			return 4;
		case "May":
			return 5;
		case "June":
			return 6;
		case "July":
			return 7;
		case "August":
			return 8;
		case "September":
			return 9;
		case "October":
			return 10;
		case "November":
			return 11;
		case "December":
			return 12;
		default:
			return 0;
		}
	}
	
	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}


	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}


	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
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
	
//	public String getEvents(User user) {
//		return "res";
//	}
	
	public void login(ActionEvent event) throws ParseException {
		int tablesCount = 0;
		switch (month) {
		case ("Show 1 month"):
			tablesCount = 1;
			break;
		case ("Show 2 months"):
			tablesCount = 2;
			break;
		case ("Show 3 months"):
			tablesCount = 3;
			break;
		}
		List<CustomDate> dates = new ArrayList<CustomDate>();
		int m = MonthToInt(tables.get(0).currentMonth);
		dates.add(new CustomDate(startYear, m));
		m+=1;
		Integer y = Integer.parseInt(startYear);
		if (m>12) {
			m=1;
			y+=1;
		}
		dates.add(new CustomDate(y.toString(), m));
		m+=1;
		if (m>12) {
			m=1;
			y+=1;
		}

		dates.add(new CustomDate(y.toString(), m));
		
		tables = null;
		tables = new ArrayList<TableContent>();
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] mss = dfs.getMonths();
		for (int i = 0; i < tablesCount; i++) {
			tables.add(new TableContent());
			tables.get(i).eventService = eventService;
			tables.get(i).currentMonth = mss[dates.get(i).month-1];
			tables.get(i).curMonthInt = MonthToInt(tables.get(i).currentMonth);
			tables.get(i).days = new ArrayList<Integer>();
			tables.get(i).currentYear = dates.get(i).year;
			int iDay = 1;
			Calendar c = new GregorianCalendar(Integer.parseInt(dates.get(i).year), dates.get(i).month-1, iDay);
			tables.get(i).daysCount = c.getActualMaximum(Calendar.DAY_OF_MONTH);
			int cnt = 1;				
			while (cnt <= tables.get(i).daysCount) {
				tables.get(i).days.add(cnt);
				cnt++;
			}
			tables.get(i).weeks = new ArrayList<Map.Entry<Integer,Integer>>();
			setWeeksMethod(tables.get(i).weeks, dates.get(i), tables.get(i).days);
			tables.get(i).weekDays = new ArrayList<String>();
			setWeekDaysMethod(tables.get(i).weekDays, dates.get(i), tables.get(i).days);
		}
		

	}
	
	

}
