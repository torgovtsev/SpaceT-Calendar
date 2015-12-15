package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.RoleEntity;

public interface RoleDAO extends AbstractDao<Integer, RoleEntity>
{

    List<RoleEntity> getAllRoles();

    RoleEntity getRoleByName(String name);

}
