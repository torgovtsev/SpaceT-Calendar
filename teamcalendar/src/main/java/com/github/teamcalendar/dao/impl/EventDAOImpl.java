package com.github.teamcalendar.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.github.teamcalendar.dao.EventDAO;
import com.github.teamcalendar.domain.EventEntity;
import com.github.teamcalendar.middleware.dto.EventType;

@Repository("eventDAO")
public class EventDAOImpl extends AbstractDaoImpl<Integer, EventEntity> implements EventDAO
{

    @SuppressWarnings("unchecked")
    public List<EventEntity> getAllEvents()
    {
        Criteria criteria = getCriteria();
        List<EventEntity> events = (List<EventEntity>)criteria.list();

        return events;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<EventEntity> getEventsByUser(Integer user_id)
    {
        Criteria crit = getCriteria();
        crit.add(Restrictions.eq("user.id", user_id));
        //crit.add(Restrictions.eq("created_on", "18.12.2015"));
        return (List<EventEntity>)crit.list();
    }

	@Override
	public List<EventEntity> getEventByUserDate(Integer uid, List<Date> d) {
		Criteria crit = getCriteria();
        crit.add(Restrictions.eq("user.id", uid));
        if (d.size() == 1)
        	crit.add(Restrictions.eq("date", d.get(0)));
        else {
        	crit.add(Restrictions.ge("date", d.get(0)));
        	crit.add(Restrictions.le("date", d.get(1)));
        }
        List<EventEntity> e = (List<EventEntity>)crit.list();
        return e;
	}

	@Override
	public List<EventEntity> getEventsByTypeDate(EventType etype, Integer year,
			Integer month) {
		Criteria crit = getCriteria();
        crit.add(Restrictions.eq("eventType.id", etype.getId()));
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);  
	    calendar.set(Calendar.SECOND, 0);  
	    calendar.set(Calendar.MILLISECOND, 0);
	    Date d1 = calendar.getTime();
	    
	    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
	    calendar.set(Calendar.HOUR_OF_DAY, 23);
	    Date d2 = calendar.getTime();
    	crit.add(Restrictions.ge("date", d1));
    	crit.add(Restrictions.le("date", d2));
        List<EventEntity> e = (List<EventEntity>)crit.list();
        return e;
	}

}
