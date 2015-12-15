package com.github.teamcalendar.dao;

import java.util.List;

import com.github.teamcalendar.domain.PermissionEntity;

public interface PermissionDAO extends AbstractDao<Integer, PermissionEntity>
{

    List<PermissionEntity> getAllPermissions();

    PermissionEntity getPermissionByName(String name);

}
