package com.github.teamcalendar.dao;

import java.util.List;
import java.util.Set;

import com.github.teamcalendar.domain.GroupEntity;
import com.github.teamcalendar.middleware.dto.Group;
import com.github.teamcalendar.middleware.dto.User;

public interface GroupDAO
{

    void addGroup(GroupEntity group);

    void updateGroup(GroupEntity group);

    void deleteGroup(GroupEntity group);

    List<GroupEntity> getAllGroups();

    GroupEntity getGroupById(Integer id);

    GroupEntity getGroupByName(String name);
}
