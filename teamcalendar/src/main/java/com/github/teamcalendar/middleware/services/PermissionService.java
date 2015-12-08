package com.github.teamcalendar.middleware.services;

import java.util.List;

import com.github.teamcalendar.middleware.dto.Permission;
import com.github.teamcalendar.middleware.services.impl.PermissionServiceImpl;

/**
 * @author Artem Ivanov
 * @see {@link PermissionServiceImpl}
 */
public interface PermissionService
{

    boolean addPermission(Permission permission);

    boolean updatePermission(Permission permission);

    boolean deletePermission(Permission permission);

    List<Permission> getAllPermissions();

    Permission getPermissionById(Integer id);

    Permission getPermissionByName(String name);

}
