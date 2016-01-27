package com.github.teamcalendar.frontend.component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
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
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.teamcalendar.middleware.dto.Event;
import com.github.teamcalendar.middleware.dto.EventType;
import com.github.teamcalendar.middleware.dto.Information;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.services.CalendarEventService;
import com.github.teamcalendar.middleware.services.EventTypeService;
import com.github.teamcalendar.middleware.services.UsersService;

@ManagedBean(name = "editEventsBean")
@Component(value = "editEventsBean")
@RequestScoped
public class EditEventsBean
{

    private final String     STRING_EDITEVENTS_SAVED   = "Saved";
    
    
    public Integer                              currentMonthInt;
    public List<Integer>                        days;
    public List<String>                         weekDays;

    private String                              currentMonth;
    private String                              currentUser;
    private String                              currentYear;
    private Calendar                            calendar;
    private List<EventType>                     eventTypes;
    private List<Event>                         userEvents;
    private Map<Integer, Map<Integer, Boolean>> userEventsByDay    = new HashMap<Integer, Map<Integer, Boolean>>();
    private User                                user;
    private List<Map.Entry<Integer, Integer>>   weeks;

    @Autowired
    private UsersService                        usersService;
    @Autowired
    private EventTypeService                    eventTypeService;
    @Autowired
    private CalendarEventService                eventService;


    public void onLoad() throws ParseException
    {
        eventTypes = eventTypeService.getAllEventTypes();

        List<Date> period = new ArrayList<Date>();

        Calendar c = new GregorianCalendar(Integer.valueOf(currentYear), MonthToInt(currentMonth) - 1, 1);
        int daysCount = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date startDate = c.getTime();

        c.set(Calendar.DAY_OF_MONTH, daysCount);

        Date endDate = c.getTime();
        period.add(startDate);
        period.add(endDate);

        user = usersService.getUserByEmail(currentUser);
        userEvents = eventService.getEventByUserDate(user.getId(), period);
        
        userEventsByDay.clear();
        days = new ArrayList<>();
        for (int i = 0; i < daysCount; i++)
        {
            Integer date = i + 1;
            days.add(date);

            Map<Integer, Boolean> qMap = new HashMap<>();
            for (Event e : userEvents)
            {
                if (e.getDate().getDate() == date)
                {
                    qMap.put(e.getEventType().getId(), true);
                    break;
                }
                else 
                {
                    qMap.put(e.getEventType().getId(), false);
                }
            }
                userEventsByDay.put(date, qMap);
        }

        weeks = new ArrayList<Map.Entry<Integer, Integer>>();
        CustomDate cd = new CustomDate(currentYear, MonthToInt(currentMonth));
        setWeeksMethod(this.weeks, cd, this.days);

        weekDays = new ArrayList<>();
        setWeekDaysMethod(this.weekDays, cd, this.days);
        
        
    }

    private Integer MonthToInt(String m)
    {
        switch (m)
        {
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

    public void apply(ActionEvent event) throws ParseException
    {
        List<Event> userEventsToDelete = new ArrayList<>();
        List<Event> userEventsToAdd = new ArrayList<>();

        
        for (Event userEvent : userEvents)
        {
            Integer eventTypeId = userEvent.getEventType().getId();
            Integer day = userEvent.getDate().getDate();

            if (userEventsByDay.get(day).get(eventTypeId))
            {
                userEventsByDay.get(day).put(eventTypeId, false);
            }
            else
            {
                userEventsToDelete.add(userEvent);
            }
        }
        
        for (Entry<Integer, Map<Integer, Boolean>> entry : userEventsByDay.entrySet())
        {
            Integer day = entry.getKey();
            for (Entry<Integer, Boolean> innerEntry : entry.getValue().entrySet())
            {
                if (innerEntry.getValue())
                {
                    Event newEvent = new Event();
                    EventType eventType = new EventType();
                    eventType.setId(innerEntry.getKey());

                    Information information = new Information();
                    information.setMessage("");
                    Integer id = eventService.AddInfo(information);
                    information = eventService.getInfoById(id);

                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.YEAR, Integer.valueOf(currentYear));
                    cal.set(Calendar.MONTH, MonthToInt(currentMonth) - 1);
                    cal.set(Calendar.DAY_OF_MONTH, day);
                    cal.set(Calendar.HOUR_OF_DAY, 0);
                    cal.set(Calendar.MINUTE, 0);
                    cal.set(Calendar.SECOND, 0);
                    cal.set(Calendar.MILLISECOND, 0);
                    Date dateRepresentation = cal.getTime();
                    newEvent.setDate(dateRepresentation);
                    newEvent.setEventType(eventType);
                    newEvent.setUser(user);
                    newEvent.setInfo(information);

                    userEventsToAdd.add(newEvent);
                }
            }
        }
        
        for (Event e : userEventsToDelete)
        {
            eventService.deleteEvent(e);
        }
        
        for (Event e : userEventsToAdd)
        {
            eventService.addEvent(e);
        }
        
        FacesMessage message = null;
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, STRING_EDITEVENTS_SAVED, "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    @PostConstruct
    public void init()
    {
        calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"));
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
    }

    private void setWeeksMethod(List<Map.Entry<Integer, Integer>> weeks, CustomDate cd, List<Integer> days) throws ParseException
    {
        int week = 0;
        Integer prevWeek = 0;
        Integer cnt = 1;
        int prevCnt = 0;
        for (Integer day : days)
        {
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
            if (prevWeek != 0) if (week == prevWeek)
            {
                cnt++;
            }
            else
            {
                prevCnt = cnt;
                cnt = 1;
                weeks.add(new AbstractMap.SimpleEntry<Integer, Integer>(prevWeek, prevCnt));
            }
        }
        Map.Entry<Integer, Integer> entry = weeks.get(weeks.size() - 1);
        if (entry.getKey() == week) entry.setValue(prevCnt + 1);
        else weeks.add(new AbstractMap.SimpleEntry<Integer, Integer>(week, cnt));
    }

    private void setWeekDaysMethod(List<String> weeks, CustomDate cd, List<Integer> days) throws ParseException
    {
        for (Integer day : days)
        {
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

            switch (weekDay)
            {
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

    public String getSymbol(Event event, Integer dayy)
    {
        if (event == null)
        {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(currentYear));
            calendar.set(Calendar.MONTH, currentMonthInt - 1);
            calendar.set(Calendar.DAY_OF_MONTH, dayy);
            int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);
            return (dayWeek == 1 || dayWeek == 7) ? "" : "8";
        }
        else
        {
            String res = "";

            return res = res + event.getEventType().getName().charAt(0);
        }
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

    public List<EventType> getEventTypes()
    {
        return eventTypes;
    }

    public void setEventTypes(List<EventType> eventTypes)
    {
        this.eventTypes = eventTypes;
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

    public List<Integer> getDays()
    {
        return days;
    }

    public void setDays(List<Integer> days)
    {
        this.days = days;
    }

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

    public Map<Integer, Map<Integer, Boolean>> getUserEventsByDay()
    {
        return userEventsByDay;
    }

    public void setUserEventsByDay(Map<Integer, Map<Integer, Boolean>> userEventsByDay)
    {
        this.userEventsByDay = userEventsByDay;
    }

}
