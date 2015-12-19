package com.github.teamcalendar.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.github.teamcalendar.dao.EventDAO;
import com.github.teamcalendar.dao.EventTypeDAO;
import com.github.teamcalendar.domain.EventEntity;
import com.github.teamcalendar.domain.EventTypeEntity;

@Repository("eventtypeDAO")
public class EventTypeDAOImpl extends AbstractDaoImpl<String, EventTypeEntity> implements EventTypeDAO {

	@Override
	public List<EventTypeEntity> getAllEventTypes() {
        Criteria criteria = getCriteria();
        List<EventTypeEntity> eventtypes = (List<EventTypeEntity>)criteria.list();

        return eventtypes;
	}

}
