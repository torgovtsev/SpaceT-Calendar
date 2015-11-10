package com.github.teamcalendar.dao;

import java.util.List;
import java.util.Set;

import com.github.teamcalendar.domain.RoleEntity;
import com.github.teamcalendar.middleware.dto.Role;
import com.github.teamcalendar.middleware.dto.User;

public interface RoleDAO
{

    void addRole(RoleEntity role);

    void updateRole(RoleEntity role);

    void deleteRole(RoleEntity role);

    List<RoleEntity> getAllRoles();

    RoleEntity getRoleById(Integer id);

    RoleEntity getRoleByName(String name);

}
