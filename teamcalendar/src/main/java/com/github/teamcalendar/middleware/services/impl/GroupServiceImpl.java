package com.github.teamcalendar.middleware.services.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.teamcalendar.dao.GroupDAO;
import com.github.teamcalendar.middleware.dto.Group;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.services.GroupService;

@Service("groupService")
@Transactional
public class GroupServiceImpl implements GroupService
{

    @Autowired
    GroupDAO dao;

    public void addGroup(Group group)
    {
        dao.addGroup(group);
    }

    public void updateRole(Group group)
    {
        dao.updateRole(group);
    }

    public void deleteRole(Group group)
    {
        dao.deleteRole(group);
    }

    public List<Group> getAllGroup()
    {
        return dao.getAllGroup();
    }

    public Group getGroupById(Integer id)
    {
        return dao.getGroupById(id);
    }

    public Group getGroupByName(String name)
    {
        return dao.getGroupByName(name);
    }

    public Set<Group> getGroupForUser(User user)
    {
        return dao.getGroupForUser(user);
    }

    public void updateGroupForUser(User user, List<Group> userGroups)
    {
        dao.updateGroupForUser(user, userGroups);
    }

}
