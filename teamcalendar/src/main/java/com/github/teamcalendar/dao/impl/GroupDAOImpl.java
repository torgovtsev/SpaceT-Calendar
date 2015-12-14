package com.github.teamcalendar.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.github.teamcalendar.dao.GroupDAO;
import com.github.teamcalendar.domain.GroupEntity;

@Repository("groupDAO")
public class GroupDAOImpl extends AbstractDaoImpl<Integer, GroupEntity> implements GroupDAO
{

    @SuppressWarnings("unchecked")
    public List<GroupEntity> getAllGroups()
    {
        Criteria criteria = getCriteria();
        List<GroupEntity> groups = (List<GroupEntity>)criteria.list();

        return groups;
    }

    public GroupEntity getGroupByName(String name)
    {
        Criteria crit = getCriteria();
        crit.add(Restrictions.eq("name", name));
        GroupEntity group = (GroupEntity)crit.uniqueResult();

        return group;
    }

}
