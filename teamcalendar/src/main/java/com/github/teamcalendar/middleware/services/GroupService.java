package com.github.teamcalendar.middleware.services;

import java.util.List;
import java.util.Set;

import com.github.teamcalendar.middleware.dto.Group;
import com.github.teamcalendar.middleware.dto.User;

public interface GroupService
{

    void addGroup(Group group);

    void updateGroup(Group group);

    void deleteGroup(Group group);

    List<Group> getAllGroups();

    Group getGroupById(Integer id);

    Group getGroupByName(String name);

}
