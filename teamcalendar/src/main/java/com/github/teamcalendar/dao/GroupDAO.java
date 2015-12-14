package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.GroupEntity;

public interface GroupDAO extends AbstractDao<Integer, GroupEntity>
{

    void create(GroupEntity group);

    void update(GroupEntity group);

    void delete(GroupEntity group);

    List<GroupEntity> getAllGroups();

    GroupEntity getById(Integer id);

    GroupEntity getGroupByName(String name);
}
