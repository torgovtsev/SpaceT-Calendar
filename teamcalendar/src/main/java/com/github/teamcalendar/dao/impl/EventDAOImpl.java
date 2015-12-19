package com.github.teamcalendar.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.github.teamcalendar.dao.EventDAO;
import com.github.teamcalendar.domain.EventEntity;

@Repository("eventDAO")
public class EventDAOImpl extends AbstractDaoImpl<String, EventEntity> implements EventDAO {
	
    @SuppressWarnings("unchecked")
    public List<EventEntity> getAllEvents()
    {
        Criteria criteria = getCriteria();
        List<EventEntity> events = (List<EventEntity>)criteria.list();

        return events;
    }

	@Override
	@SuppressWarnings("unchecked")
	public List<EventEntity> getEventsByUser(Integer user_id) {
		Criteria crit = getCriteria();
		crit.add(Restrictions.eq("user.id", user_id));
		//crit.add(Restrictions.eq("created_on", "18.12.2015"));
		return (List<EventEntity>)crit.list();
	}
    
    
}
