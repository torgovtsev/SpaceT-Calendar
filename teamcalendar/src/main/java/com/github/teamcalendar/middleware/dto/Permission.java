package com.github.teamcalendar.middleware.dto;

import java.util.HashSet;
import java.util.Set;

public class Permission
{

    private Integer   id;
    private String    name;
    private String    description;

    private Set<Role> rolePermission = new HashSet<Role>(0);

    public Permission()
    {

    }

    public Permission(Integer id, String name, String description)
    {
        super();
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

    public Set<Role> getRolePermission()
    {
        return rolePermission;
    }

    public void setRolePermission(Set<Role> rolePermission)
    {
        this.rolePermission = rolePermission;
    }

}
