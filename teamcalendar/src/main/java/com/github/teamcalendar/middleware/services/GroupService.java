package com.github.teamcalendar.middleware.services;

import java.util.List;

import com.github.teamcalendar.middleware.dto.Group;
import com.github.teamcalendar.middleware.services.impl.GroupServiceImpl;

/**
 * @author Artem Ivanov
 * @see {@link GroupServiceImpl}
 */
public interface GroupService
{

    boolean addGroup(Group group);

    boolean updateGroup(Group group);

    boolean deleteGroup(Group group);

    List<Group> getAllGroups();

    Group getGroupById(Integer id);

    Group getGroupByName(String name);

}
