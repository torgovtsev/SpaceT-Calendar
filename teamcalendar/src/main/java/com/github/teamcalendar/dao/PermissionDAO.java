package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.PermissionEntity;

public interface PermissionDAO extends AbstractDao<Integer, PermissionEntity>
{

    void create(PermissionEntity permission);

    void update(PermissionEntity permission);

    void delete(PermissionEntity permission);

    List<PermissionEntity> getAllPermissions();

    PermissionEntity getById(Integer id);

    PermissionEntity getPermissionByName(String name);

}
