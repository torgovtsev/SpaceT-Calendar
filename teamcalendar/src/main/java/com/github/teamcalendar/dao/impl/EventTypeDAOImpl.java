package com.github.teamcalendar.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.github.teamcalendar.dao.EventTypeDAO;
import com.github.teamcalendar.domain.EventTypeEntity;
import com.github.teamcalendar.domain.UserEntity;

@Repository("eventtypeDAO")
public class EventTypeDAOImpl extends AbstractDaoImpl<Integer, EventTypeEntity> implements EventTypeDAO
{

    @Override
    public List<EventTypeEntity> getAllEventTypes()
    {
        Criteria criteria = getCriteria();
        List<EventTypeEntity> eventtypes = (List<EventTypeEntity>)criteria.list();

        return eventtypes;
    }

	@Override
	public EventTypeEntity getByName(String name) {
		Criteria crit = getCriteria();
        crit.add(Restrictions.eq("name", name));
        EventTypeEntity etent = (EventTypeEntity)crit.uniqueResult();

        return etent;
	}
    
    

}
