package com.github.teamcalendar.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.github.teamcalendar.dao.EventDAO;
import com.github.teamcalendar.domain.EventEntity;

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
        //if (e.size() > 0)
        	//return e.get(0);
        //return null;
        return e;
	}

}
