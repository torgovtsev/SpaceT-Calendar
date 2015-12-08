package com.github.teamcalendar.middleware.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.teamcalendar.dao.PermissionDAO;
import com.github.teamcalendar.domain.PermissionEntity;
import com.github.teamcalendar.middleware.dto.Permission;
import com.github.teamcalendar.middleware.services.PermissionService;
import com.github.teamcalendar.middleware.utils.MapperService;

/**
 * @author Artem Ivanov
 *
 */
@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements PermissionService
{

    private static final Logger LOG = LoggerFactory.getLogger(PermissionEntity.class);

    @Autowired
    PermissionDAO               dao;

    /**
     * @param permission
     *            if a null object parameter is passed to method, nothing will happen
     */
    public void addPermission(Permission permission)
    {
        if (permission == null)
        {
            return;
        }
        PermissionEntity permissionEntity = convertPermissionToEntity(permission);
        dao.addPermission(permissionEntity);
    }

    /**
     * @param permission
     *            if a null object parameter is passed to method, nothing will happen
     */
    public void updatePermission(Permission permission)
    {
        if (permission == null)
        {
            return;
        }
        PermissionEntity permissionEntity = convertPermissionToEntity(permission);
        dao.updatePermission(permissionEntity);
    }

    /**
     * @param permission
     *            if a null object parameter is passed to method, nothing will happen
     */
    public void deletePermission(Permission permission)
    {
        if (permission == null)
        {
            return;
        }
        PermissionEntity permissionEntity = convertPermissionToEntity(permission);
        dao.deletePermission(permissionEntity);
    }

    /**
     * @return <code>null</code> if there is no entries in table
     */
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

    /**
     * @return <code>null</code> if there is no entry for such id
     */
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

    /**
     * @return <code>null</code> if there is no permission with name
     * @param name
     *            permission name (view, edit, add, remove, etc)
     */
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
