package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.GroupEntity;

public interface GroupDAO extends AbstractDao<Integer, GroupEntity>
{

    List<GroupEntity> getAllGroups();

    GroupEntity getGroupByName(String name);
}
