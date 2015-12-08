package com.github.teamcalendar.middleware.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.teamcalendar.dao.GroupDAO;
import com.github.teamcalendar.domain.GroupEntity;
import com.github.teamcalendar.middleware.dto.Group;
import com.github.teamcalendar.middleware.services.GroupService;
import com.github.teamcalendar.middleware.utils.MapperService;

/**
 * @author Artem Ivanov
 *
 */
@Service("groupService")
@Transactional
public class GroupServiceImpl implements GroupService
{

    private static final Logger LOG = LoggerFactory.getLogger(GroupEntity.class);

    @Autowired
    GroupDAO                    dao;

    /**
     * @param group
     *            if a null object parameter is passed to method, nothing will happen
     * @return true in case of operation success
     */
    public boolean addGroup(Group group)
    {
        if (group == null)
        {
            return false;
        }
        try
        {
            GroupEntity groupEntity = convertGroupToEntity(group);
            dao.addGroup(groupEntity);
            return true;
        }
        catch (Exception e)
        {
            LOG.error("Failed to add group");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * @param group
     *            if a null object parameter is passed to method, nothing will happen
     * @return true in case of operation success
     */
    public boolean updateGroup(Group group)
    {
        if (group == null)
        {
            return false;
        }
        try
        {
            GroupEntity groupEntity = convertGroupToEntity(group);
            dao.updateGroup(groupEntity);
            return true;
        }
        catch (Exception e)
        {
            LOG.error("Failed to update group");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * @param group
     *            if a null object parameter is passed to method, nothing will happen
     * @return true in case of operation success
     */
    public boolean deleteGroup(Group group)
    {
        if (group == null)
        {
            return false;
        }

        try
        {
            GroupEntity groupEntity = convertGroupToEntity(group);
            dao.deleteGroup(groupEntity);
            return true;
        }
        catch (Exception e)
        {
            LOG.error("Failed to delete group");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @return <code>null</code> if there is no groups in table
     */
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

    /**
     * @return <code>null</code> if there is no entry for such id
     */
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

    /**
     * @return <code>null</code> if there is no role with name
     * @param name
     *            group name (admin, user, etc)
     */
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
