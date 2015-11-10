package com.github.teamcalendar.middleware.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.teamcalendar.dao.PermissionDAO;
import com.github.teamcalendar.domain.PermissionEntity;
import com.github.teamcalendar.domain.QuestionEntity;
import com.github.teamcalendar.middleware.dto.Permission;
import com.github.teamcalendar.middleware.dto.Question;
import com.github.teamcalendar.middleware.dto.Role;
import com.github.teamcalendar.middleware.services.PermissionService;
import com.github.teamcalendar.middleware.utils.MapperService;

@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements PermissionService
{

    private static final Logger LOG = LoggerFactory.getLogger(PermissionEntity.class);

    @Autowired
    PermissionDAO               dao;

    public void addPermission(Permission permission)
    {
        PermissionEntity permissionEntity = convertPermissionToEntity(permission);
        dao.addPermission(permissionEntity);
    }

    public void updatePermission(Permission permission)
    {
        PermissionEntity permissionEntity = convertPermissionToEntity(permission);
        dao.updatePermission(permissionEntity);
    }

    public void deletePermission(Permission permission)
    {
        PermissionEntity permissionEntity = convertPermissionToEntity(permission);
        dao.deletePermission(permissionEntity);
    }

    public List<Permission> getAllPermissions()
    {

        final List<Permission> result = new ArrayList<Permission>();

        List<PermissionEntity> permissionsEntity = dao.getAllPermissions();

        if (CollectionUtils.isEmpty(permissionsEntity))
        {
            LOG.error("NULL reference on permission");
            return result;
        }

        for (PermissionEntity data : permissionsEntity)
        {
            Permission permission = convertEntityToPermission(data);
            result.add(permission);
        }

        return result;

    }

    public Permission getPermissionById(Integer id)
    {

        Permission result = null;

        try
        {
            PermissionEntity permissionEntity = (PermissionEntity)dao.getPermissionById(id);

            if (permissionEntity != null)
            {
                result = convertEntityToPermission(permissionEntity);
            }
        }
        catch (Exception ex)
        {
            LOG.error(String.format("Error loading permission by id=%s", id), ex);
        }

        return result;
    }

    public Permission getPermissionByName(String name)
    {

        Permission result = null;

        try
        {
            PermissionEntity permissionEntity = dao.getPermissionByName(name);

            if (permissionEntity != null)
            {
                result = convertEntityToPermission(permissionEntity);
            }
        }
        catch (Exception ex)
        {
            LOG.error(String.format("Error loading permission by name=%s", name), ex);
        }
        return result;
    }

    private Permission convertEntityToPermission(PermissionEntity entity)
    {
        return MapperService.getInstance().map(entity, Permission.class);
    }

    private PermissionEntity convertPermissionToEntity(Permission permission)
    {
        return MapperService.getInstance().map(permission, PermissionEntity.class);
    }

}
