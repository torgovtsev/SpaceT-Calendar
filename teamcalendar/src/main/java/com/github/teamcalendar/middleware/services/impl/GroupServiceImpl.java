package com.github.teamcalendar.middleware.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.teamcalendar.dao.GroupDAO;
import com.github.teamcalendar.dao.RoleDAO;
import com.github.teamcalendar.domain.GroupEntity;
import com.github.teamcalendar.domain.RoleEntity;
import com.github.teamcalendar.middleware.dto.Group;
import com.github.teamcalendar.middleware.dto.Role;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.services.GroupService;
import com.github.teamcalendar.middleware.utils.MapperService;

@Service("groupService")
@Transactional
public class GroupServiceImpl implements GroupService
{

    private static final Logger LOG = LoggerFactory.getLogger(GroupEntity.class);

    @Autowired
    GroupDAO                    dao;

    public void addGroup(Group group)
    {
        GroupEntity groupEntity = convertGroupToEntity(group);
        dao.addGroup(groupEntity);
    }

    public void updateGroup(Group group)
    {
        GroupEntity groupEntity = convertGroupToEntity(group);
        dao.updateGroup(groupEntity);
    }

    public void deleteGroup(Group group)
    {
        GroupEntity groupEntity = convertGroupToEntity(group);
        dao.deleteGroup(groupEntity);
    }

    public List<Group> getAllGroups()
    {
        List<Group> result = new ArrayList<Group>();

        List<GroupEntity> groupsEntity = dao.getAllGroups();

        if (CollectionUtils.isEmpty(groupsEntity))
        {
            LOG.error("NULL reference for group");
            return result;
        }

        for (GroupEntity data : groupsEntity)
        {
            Group group = convertEntityToGroup(data);
            result.add(group);
        }
        return result;
    }

    public Group getGroupById(Integer id)
    {
        Group result = null;

        try
        {
            GroupEntity groupEntity = (GroupEntity)dao.getGroupById(id);
            if (groupEntity != null)
            {
                result = convertEntityToGroup(groupEntity);
            }
        }
        catch (Exception ex)
        {
            LOG.error(String.format("Error loading group by id=%s", id), ex);
        }

        return result;
    }

    public Group getGroupByName(String name)
    {
        Group result = null;

        try
        {
            GroupEntity groupEntity = (GroupEntity)dao.getGroupByName(name);

            if (groupEntity != null)
            {
                result = convertEntityToGroup(groupEntity);
            }
        }
        catch (Exception ex)
        {
            LOG.error(String.format("Error loading group by name=%s", name), ex);
        }

        return result;
    }

    private Group convertEntityToGroup(GroupEntity entity)
    {
        return MapperService.getInstance().map(entity, Group.class);
    }

    private GroupEntity convertGroupToEntity(Group group)
    {
        return MapperService.getInstance().map(group, GroupEntity.class);
    }

}
