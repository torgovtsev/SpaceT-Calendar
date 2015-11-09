package com.github.teamcalendar.dao;

import java.util.List;
import java.util.Set;

import com.github.teamcalendar.middleware.dto.Role;
import com.github.teamcalendar.middleware.dto.User;

public interface RoleDAO
{

    void addRole(Role role);

    void updateRole(Role role);

    void deleteRole(Role role);

    List<Role> getAllRoles();

    Role getRoleById(Integer id);

    Role getRoleByName(String name);

    Set<Role> getRolesForUser(User user);

    void updateRoleForUser(User user, List<Role> userRoles);

}