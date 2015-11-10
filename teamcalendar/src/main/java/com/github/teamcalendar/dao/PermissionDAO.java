package com.github.teamcalendar.dao;

import java.util.List;
import java.util.Set;

import com.github.teamcalendar.domain.PermissionEntity;
import com.github.teamcalendar.middleware.dto.Permission;
import com.github.teamcalendar.middleware.dto.Role;

public interface PermissionDAO
{

    void addPermission(PermissionEntity permission);

    void updatePermission(PermissionEntity permission);

    void deletePermission(PermissionEntity permission);

    List<PermissionEntity> getAllPermissions();

    PermissionEntity getPermissionById(Integer id);

    PermissionEntity getPermissionByName(String name);

}
