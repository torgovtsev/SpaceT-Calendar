package com.github.teamcalendar.middleware.dto;

import java.util.HashSet;
import java.util.Set;

public class Role
{

    private Integer         id;
    private String          name;
    private String          description;

    private Set<Permission> permissionRole = new HashSet<Permission>(0);
    private Set<User>       userRole       = new HashSet<User>(0);
    private Set<Group>      groupRole      = new HashSet<Group>(0);

    public Role()
    {

    }

    public Role(Integer id, String name, String description)
    {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Set<Permission> getPermissionRole()
    {
        return this.permissionRole;
    }

    public void setPermissionRole(Set<Permission> permission)
    {
        this.permissionRole = permission;
    }

    public Set<User> getUserRole()
    {
        return this.userRole;
    }

    public void setUserRole(Set<User> user)
    {
        this.userRole = user;
    }

    public Set<Group> getGroupRole()
    {
        return this.groupRole;
    }

    public void setGroupRole(Set<Group> group)
    {
        this.groupRole = group;
    }

}
