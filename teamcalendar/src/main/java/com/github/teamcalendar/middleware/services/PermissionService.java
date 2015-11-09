package com.github.teamcalendar.middleware.services;

import java.util.List;
import java.util.Set;

import com.github.teamcalendar.middleware.dto.Permission;
import com.github.teamcalendar.middleware.dto.Role;

public interface PermissionService
{

    void addPermission(Permission permission);

    void updatePermission(Permission permission);

    void deletePermissione(Permission permission);

    List<Permission> getAllPermissions();

    Permission getPermissionById(Integer id);

    Permission getPermissionByName(String name);

    Set<Permission> getPermissionsForRole(Role role);

    void updatePermissionsForRole(Role role, List<Permission> rolePermissions);

}
