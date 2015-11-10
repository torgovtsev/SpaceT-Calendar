package com.github.teamcalendar.middleware.services;

import java.util.List;
import java.util.Set;

import com.github.teamcalendar.middleware.dto.Role;
import com.github.teamcalendar.middleware.dto.User;

public interface RoleService
{

    void addRole(Role role);

    void updateRole(Role role);

    void deleteRole(Role role);

    List<Role> getAllRoles();

    Role getRoleById(Integer id);

    Role getRoleByName(String name);

}
