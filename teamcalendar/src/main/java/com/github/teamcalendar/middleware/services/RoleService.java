package com.github.teamcalendar.middleware.services;

import java.util.List;

import com.github.teamcalendar.middleware.dto.Role;
import com.github.teamcalendar.middleware.services.impl.RoleServiceImpl;

/**
 * @author Artem Ivanov
 * @see {@link RoleServiceImpl}
 */
public interface RoleService
{

    boolean addRole(Role role);

    boolean updateRole(Role role);

    boolean deleteRole(Role role);

    List<Role> getAllRoles();

    Role getRoleById(Integer id);

    Role getRoleByName(String name);

}
