package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.RoleEntity;

public interface RoleDAO extends AbstractDao<Integer, RoleEntity>
{

    void create(RoleEntity role);

    void update(RoleEntity role);

    void delete(RoleEntity role);

    List<RoleEntity> getAllRoles();

    RoleEntity getById(Integer id);

    RoleEntity getRoleByName(String name);

}
