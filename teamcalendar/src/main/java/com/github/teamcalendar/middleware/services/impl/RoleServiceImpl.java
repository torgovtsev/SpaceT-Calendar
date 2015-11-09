package com.github.teamcalendar.middleware.services.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.teamcalendar.dao.RoleDAO;
import com.github.teamcalendar.middleware.dto.Role;
import com.github.teamcalendar.middleware.dto.User;
import com.github.teamcalendar.middleware.services.RoleService;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService
{

    @Autowired
    RoleDAO dao;

    public void addRole(Role role)
    {
        dao.addRole(role);
    }

    public void updateRole(Role role)
    {
        dao.updateRole(role);
    }

    public void deleteRole(Role role)
    {
        dao.deleteRole(role);
    }

    public List<Role> getAllRoles()
    {
        return dao.getAllRoles();
    }

    public Role getRoleById(Integer id)
    {
        return dao.getRoleById(id);
    }

    public Role getRoleByName(String name)
    {
        return dao.getRoleByName(name);
    }

    public Set<Role> getRolesForUser(User user)
    {
        return dao.getRolesForUser(user);
    }

    public void updateRoleForUser(User user, List<Role> userRoles)
    {
        dao.updateRoleForUser(user, userRoles);
    }

}
