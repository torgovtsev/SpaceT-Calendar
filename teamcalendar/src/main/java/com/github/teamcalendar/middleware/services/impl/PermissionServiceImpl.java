package com.github.teamcalendar.middleware.services.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.teamcalendar.dao.PermissionDAO;
import com.github.teamcalendar.middleware.dto.Permission;
import com.github.teamcalendar.middleware.dto.Role;
import com.github.teamcalendar.middleware.services.PermissionService;

@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements PermissionService
{

    @Autowired
    PermissionDAO dao;

    public void addPermission(Permission permission)
    {
        dao.addPermission(permission);
    }

    public void updatePermission(Permission permission)
    {
        dao.updatePermission(permission);
    }

    public void deletePermissione(Permission permission)
    {
        dao.deletePermissione(permission);
    }

    public List<Permission> getAllPermissions()
    {
        return dao.getAllPermissions();
    }

    public Permission getPermissionById(Integer id)
    {
        return dao.getPermissionById(id);
    }

    public Permission getPermissionByName(String name)
    {
        return dao.getPermissionByName(name);
    }

    public Set<Permission> getPermissionsForRole(Role role)
    {
        return dao.getPermissionsForRole(role);
    }

    public void updatePermissionsForRole(Role role, List<Permission> rolePermissions)
    {
        dao.updatePermissionsForRole(role, rolePermissions);
    }

}
