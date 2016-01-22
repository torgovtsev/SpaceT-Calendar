package com.github.teamcalendar.frontend.component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.teamcalendar.middleware.dto.Event;
import com.github.teamcalendar.middleware.dto.EventType;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.services.CalendarEventService;
import com.github.teamcalendar.middleware.services.EventTypeService;
import com.github.teamcalendar.middleware.services.UsersService;

@ManagedBean(name = "editEventsBean")
@Component(value = "editEventsBean")
@RequestScoped
public class EditEventsBean
{
    private String           currentMonth;
    public Integer currentMonthInt;
    private String           currentUser = "stealthwar@gmail.com";
    private String           currentYear;

    private int              daysCount;
    public List<Integer>     days;
    List<Map.Entry<Integer, Integer>> weeks;
    public List<String> weekDays;

    public List<Integer> getDays()
    {
        return days;
    }

    public void setDays(List<Integer> days)
    {
        this.days = days;
    }

    private Calendar         calendar;
    private List<EventType>  etypes;
    private List<Event> userEvents;



    @Autowired
    private UsersService     usersService;

    @Autowired
    private EventTypeService etypeService;
    
    @Autowired
    private CalendarEventService eventService;
    
    public List<Event> getUserEvents()
    {
        return userEvents;
    }

    public void setUserEvents(List<Event> userEvents)
    {
        this.userEvents = userEvents;
    }


    public String getCurrentMonth()
    {
        return currentMonth;

    }

    public void setCurrentMonth(String currentMonth)
    {
        this.currentMonth = currentMonth;
    }

    public EditEventsBean()
    {
        //        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        //        String id = request.getParameter("Year");
    }

    @PostConstruct
    public void init() throws ParseException
    {
        
        calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"));
        calendar.setFirstDayOfWeek(Calendar.MONDAY);

        
        Calendar c = new GregorianCalendar(2016, 0, 1);
        daysCount = c.getActualMaximum(Calendar.DAY_OF_MONTH);

        
        days = new ArrayList<>();
        for (int i = 0; i < daysCount; i++)
        {
            days.add(i + 1);
        }
        
        

        
        etypes = etypeService.getAllEventTypes();
        weeks = new ArrayList<Map.Entry<Integer, Integer>>();
        
        
        CustomDate cd = new CustomDate("2017", 0);
        setWeeksMethod(this.weeks, cd, this.days);
        
        weekDays = new ArrayList<>();
        setWeekDaysMethod(this.weekDays, cd, this.days);
        
        User user = usersService.getUserByEmail(currentUser);
        
        Date dateStart = new Date(2016, 0, 1);
        Date dateEnd = new Date(2016, 0, 31);
        
        List<Date> lDates = new ArrayList<>();
        lDates.add(dateStart);
        lDates.add(dateEnd);
        
        userEvents = eventService.getEventByUserDate(user.getId(), lDates);
        
    }

    public List<String> getWeekDays()
    {
        return weekDays;
    }

    public void setWeekDays(List<String> weekDays)
    {
        this.weekDays = weekDays;
    }

    public List<Map.Entry<Integer, Integer>> getWeeks()
    {
        return weeks;
    }

    public void setWeeks(List<Map.Entry<Integer, Integer>> weeks)
    {
        this.weeks = weeks;
    }

    public List<EventType> getEtypes()
    {
        return etypes;
    }

    public void setEtypes(List<EventType> etypes)
    {
        this.etypes = etypes;
    }

    public Calendar getCalendar()
    {
        return calendar;
    }

    public void setCalendar(Calendar calendar)
    {
        this.calendar = calendar;
    }

    public String getCurrentYear()
    {
        return currentYear;
    }

    public void setCurrentYear(String currentYear)
    {
        this.currentYear = currentYear;
    }

    public String getCurrentUser()
    {
        return currentUser;
    }

    public void setCurrentUser(String currentUser)
    {
        this.currentUser = currentUser;
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
    
    public String getSymbol(Event event, Integer dayy) {
        if (event == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(currentYear));
            calendar.set(Calendar.MONTH, currentMonthInt-1);
            calendar.set(Calendar.DAY_OF_MONTH, dayy);
            int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);
            return (dayWeek == 1 || dayWeek == 7) ? "" : "8";
        }
        else {
            String res = "";
            
            return res = res + event.getEventType().getName().charAt(0);
        }
    }
    

}