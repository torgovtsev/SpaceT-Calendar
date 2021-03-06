package com.github.teamcalendar.middleware.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.teamcalendar.dao.RoleDAO;
import com.github.teamcalendar.domain.RoleEntity;
import com.github.teamcalendar.middleware.dto.Role;
import com.github.teamcalendar.middleware.services.RoleService;
import com.github.teamcalendar.middleware.utils.MapperService;

/**
 * @author Artem Ivanov
 *
 */
@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService
{

    private static final Logger LOG = LoggerFactory.getLogger(RoleEntity.class);

    @Autowired
    RoleDAO                     dao;

    /**
     * @param role
     *            if a null object parameter is passed to method, nothing will happen
     * @return true in case of operation success
     */
    public boolean addRole(Role role)
    {
        if (role == null)
        {
            return false;
        }
        try
        {
            RoleEntity roleEntity = convertRoleToEntity(role);
            dao.create(roleEntity);
            return true;
        }
        catch (Exception e)
        {
            LOG.error("Failed to add role");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * @param role
     *            if a null object parameter is passed to method, nothing will happen
     * @return true in case of operation success
     */
    public boolean updateRole(Role role)
    {
        if (role == null)
        {
            return false;
        }
        try
        {
            RoleEntity roleEntity = convertRoleToEntity(role);
            dao.update(roleEntity);
            return true;
        }
        catch (Exception e)
        {
            LOG.error("Failed to update role");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * @param role
     *            if a null object parameter is passed to method, nothing will happen
     * @return true in case of operation success
     */
    public boolean deleteRole(Role role)
    {
        if (role == null)
        {
            return false;
        }
        try
        {
            RoleEntity roleEntity = convertRoleToEntity(role);
            dao.delete(roleEntity);
            return true;
        }
        catch (Exception e)
        {
            LOG.error("Failed to delete role");
            e.printStackTrace();
        }

        return false;
    }

    /**
     * @return <code>null</code> if there is no roles in table
     */
    public List<Role> getAllRoles()
    {

        final List<Role> result = new ArrayList<Role>();

        List<RoleEntity> rolesEntity = dao.getAllRoles();

        if (CollectionUtils.isEmpty(rolesEntity))
        {
            LOG.error("NULL reference on roles");
            return result;
        }

        for (RoleEntity data : rolesEntity)
        {
            Role role = convertEntityToRole(data);
            result.add(role);
        }

        return result;
    }

    /**
     * @return <code>null</code> if there is no entry for id
     */
    public Role getRoleById(Integer id)
    {

        Role result = null;

        try
        {
            RoleEntity roleEntity = (RoleEntity)dao.getById(id);
            if (roleEntity != null)
            {
                result = convertEntityToRole(roleEntity);
            }
        }
        catch (Exception ex)
        {
            LOG.error(String.format("Error loading role by id=%s", id), ex);
        }

        return result;
    }

    /**
     * @return <code>null</code> if there is no role with name
     * @param name
     *            permission name (view, edit, add, remove, etc)
     */
    public Role getRoleByName(String name)
    {

        Role result = null;

        try
        {
            RoleEntity roleEntity = (RoleEntity)dao.getRoleByName(name);
            if (roleEntity != null)
            {
                result = convertEntityToRole(roleEntity);
            }
        }
        catch (Exception ex)
        {
            LOG.error(String.format("Error loading role by name=%s", name), ex);
        }
        return result;
    }

    private Role convertEntityToRole(RoleEntity entity)
    {
        return MapperService.getInstance().map(entity, Role.class);
    }

    private RoleEntity convertRoleToEntity(Role role)
    {
        return MapperService.getInstance().map(role, RoleEntity.class);
    }

}
