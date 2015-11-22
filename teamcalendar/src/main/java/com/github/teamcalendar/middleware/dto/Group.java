package com.github.teamcalendar.middleware.dto;

import java.util.HashSet;
import java.util.Set;

public class Group
{

    private Integer   id;
    private String    name;
    private String    description;

    private Set<Role> roleGroup = new HashSet<Role>(0);
    private Set<User> userGroup = new HashSet<User>(0);

    public Group()
    {

    }

    public Group(Integer id, String name, String description)
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

    public Set<Role> getRoleGroup()
    {
        return roleGroup;
    }

    public void setRoleGroup(Set<Role> roleGroup)
    {
        this.roleGroup = roleGroup;
    }

    public Set<User> getUserGroup()
    {
        return userGroup;
    }

    public void setUserGroup(Set<User> userGroup)
    {
        this.userGroup = userGroup;
    }

}
