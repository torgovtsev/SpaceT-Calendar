package com.github.teamcalendar.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.github.teamcalendar.dao.GroupDAO;
import com.github.teamcalendar.domain.GroupEntity;
import com.github.teamcalendar.domain.RoleEntity;
import com.github.teamcalendar.middleware.dto.Country;
import com.github.teamcalendar.middleware.dto.Group;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.utils.MapperService;

@Repository("groupDAO")
public class GroupDAOImpl extends AbstractDao<Integer, GroupEntity> implements GroupDAO
{

    public void addGroup(GroupEntity group)
    {
        persist(group);
    }

    public void updateGroup(GroupEntity group)
    {
        update(group);
    }

    public void deleteGroup(GroupEntity group)
    {
        delete(group);
    }

    @SuppressWarnings("unchecked")
    public List<GroupEntity> getAllGroups()
    {
        Criteria criteria = createEntityCriteria();
        List<GroupEntity> groups = (List<GroupEntity>)criteria.list();

        return groups;
    }

    public GroupEntity getGroupById(Integer id)
    {
        GroupEntity group = getByKey(id);
        return group;
    }

    public GroupEntity getGroupByName(String name)
    {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("name", name));
        GroupEntity group = (GroupEntity)crit.uniqueResult();

        return group;
    }

}
