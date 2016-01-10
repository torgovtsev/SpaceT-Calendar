package com.github.teamcalendar.middleware.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.teamcalendar.dao.EventTypeDAO;
import com.github.teamcalendar.domain.EventTypeEntity;
import com.github.teamcalendar.domain.GroupEntity;
import com.github.teamcalendar.middleware.dto.EventType;
import com.github.teamcalendar.middleware.services.EventTypeService;
import com.github.teamcalendar.middleware.utils.MapperService;

/**
 * @author Artem Ivanov
 *
 */
@Service("eventTypeService")
@Transactional
public class EventTypeServiceImpl implements EventTypeService
{
    private static final Logger LOG = LoggerFactory.getLogger(GroupEntity.class);

    @Autowired
    EventTypeDAO                dao;

    /**
     * @param eventType
     *            if a null object parameter is passed to method, nothing will happen
     * @return true in case of operation success
     */
    public boolean addEventType(EventType eventType)
    {
        if (eventType == null)
        {
            return false;
        }
        try
        {
            EventTypeEntity eventTypeEntity = convertEvent_TypeToEntity(eventType);
            dao.create(eventTypeEntity);
            return true;
        }
        catch (Exception e)
        {
            LOG.error("Failed to add eventType");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * @param eventType
     *            if a null object parameter is passed to method, nothing will happen
     * @return true in case of operation success
     */
    public boolean updateEventType(EventType eventType)
    {
        if (eventType == null)
        {
            return false;
        }
        try
        {
            EventTypeEntity eventTypeEntity = convertEvent_TypeToEntity(eventType);
            dao.update(eventTypeEntity);
            return true;
        }
        catch (Exception e)
        {
            LOG.error("Failed to update eventType");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * @param eventType
     *            if a null object parameter is passed to method, nothing will happen
     * @return true in case of operation success
     */
    public boolean deleteEventType(EventType eventType)
    {
        if (eventType == null)
        {
            return false;
        }

        try
        {
            EventTypeEntity eventTypeEntity = convertEvent_TypeToEntity(eventType);
            dao.delete(eventTypeEntity);
            return true;
        }
        catch (Exception e)
        {
            LOG.error("Failed to delete eventType");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @return <code>null</code> if there is no eventTypes in table
     */
    public List<EventType> getAllEventTypes()
    {
        List<EventType> result = new ArrayList<EventType>();

        List<EventTypeEntity> eventTypesEntity = dao.getAllEventTypes();

        if (CollectionUtils.isEmpty(eventTypesEntity))
        {
            LOG.error("NULL reference for eventType");
            return result;
        }

        for (EventTypeEntity data : eventTypesEntity)
        {
            EventType eventType = convertEntityToEvent_Type(data);
            result.add(eventType);
        }
        return result;
    }

    /**
     * @return <code>null</code> if there is no entry for such id
     */
    public EventType getEventTypeById(Integer id)
    {
        EventType result = null;

        try
        {
            EventTypeEntity eventTypeEntity = (EventTypeEntity)dao.getById(id);
            if (eventTypeEntity != null)
            {
                result = convertEntityToEvent_Type(eventTypeEntity);
            }
        }
        catch (Exception ex)
        {
            LOG.error(String.format("Error loading eventType by id=%s", id), ex);
        }

        return result;
    }
    
    public EventType getEventTypeByName(String name) {
    	EventType result = null;

        try
        {
            EventTypeEntity eventTypeEntity = (EventTypeEntity)dao.getByName(name);
            if (eventTypeEntity != null)
            {
                result = convertEntityToEvent_Type(eventTypeEntity);
            }
        }
        catch (Exception ex)
        {
            LOG.error(String.format("Error loading eventType by name=%s", name), ex);
        }

        return result;
    }

    private EventType convertEntityToEvent_Type(EventTypeEntity entity)
    {
        return MapperService.getInstance().map(entity, EventType.class);
    }

    private EventTypeEntity convertEvent_TypeToEntity(EventType eventType)
    {
        return MapperService.getInstance().map(eventType, EventTypeEntity.class);
    }

}
