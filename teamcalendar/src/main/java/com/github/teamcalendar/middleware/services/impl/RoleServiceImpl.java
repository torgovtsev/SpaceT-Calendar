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

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService
{

    private static final Logger LOG = LoggerFactory.getLogger(RoleEntity.class);

    @Autowired
    RoleDAO                     dao;

    public void addRole(Role role)
    {
        if (role == null)
        {
            return;
        }
        RoleEntity roleEntity = convertRoleToEntity(role);
        dao.addRole(roleEntity);
    }

    public void updateRole(Role role)
    {
        if (role == null)
        {
            return;
        }
        RoleEntity roleEntity = convertRoleToEntity(role);
        dao.updateRole(roleEntity);
    }

    public void deleteRole(Role role)
    {
        if (role == null)
        {
            return;
        }
        RoleEntity roleEntity = convertRoleToEntity(role);
        dao.deleteRole(roleEntity);
    }

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

    public Role getRoleById(Integer id)
    {

        Role result = null;

        try
        {
            RoleEntity roleEntity = (RoleEntity)dao.getRoleById(id);
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
