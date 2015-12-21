package com.github.teamcalendar.frontend.component;

import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.teamcalendar.middleware.dto.EventType;
import com.github.teamcalendar.middleware.services.EventTypeService;

@ManagedBean
@Component(value = "eventTypeBean")
public class EventTypeBean
{
    @Autowired
    EventTypeService  eventTypeService;

    private EventType eventType = new EventType();

    public EventType getEventType()
    {
        return eventType;
    }

    public void setEventType(EventType eventType)
    {
        this.eventType = eventType;
    }

    public String newEventType()
    {
        this.eventType = new EventType();
        return "addEventType?faces-redirect=true";
    }

    public List<EventType> getAllEventTypes()
    {
        try
        {
            List<EventType> eventTypes = eventTypeService.getAllEventTypes();
            return eventTypes;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public String create()
    {
        eventType.setName(eventType.getDescription().substring(0, 1));
        eventTypeService.addEventType(eventType);
        return "EventTypeList?faces-redirect=true";
    }

    public String editEventType()
    {
        Integer editId = this.eventType.getId();
        this.eventType = eventTypeService.getEventTypeById(editId);

        return "editEventType?faces-redirect=true";
    }

    public String delete()
    {
        eventTypeService.deleteEventType(eventType);
        return "EventTypeList?faces-redirect=true";
    }

    public String cancel()
    {
        return "EventTypeList?faces-redirect=true";
    }

    public String update()
    {
        eventTypeService.updateEventType(eventType);
        return "EventTypeList?faces-redirect=true";
    }
}
