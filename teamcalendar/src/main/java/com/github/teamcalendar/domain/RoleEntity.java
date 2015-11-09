package com.github.teamcalendar.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ROLE", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class RoleEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer                   id;

    @Column(name = "NAME", unique = true, nullable = false, length = 64)
    private String                    name;

    @Column(name = "DESCRIPTION", length = 512)
    private String                    description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<RolePermissionEntity> rolePermissionEntity = new HashSet<RolePermissionEntity>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<UserRoleEntity>       userRoleEntity       = new HashSet<UserRoleEntity>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private Set<GroupRoleEntity>      groupRoleEntity      = new HashSet<GroupRoleEntity>(0);

    public RoleEntity()
    {
    }

    public RoleEntity(String name)
    {
        this.name = name;
    }

    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Set<RolePermissionEntity> getRolePermissionEntity()
    {
        return this.rolePermissionEntity;
    }

    public void setRolePermissionEntity(Set<RolePermissionEntity> rolePermissionEntity)
    {
        this.rolePermissionEntity = rolePermissionEntity;
    }

    public Set<UserRoleEntity> getUserRoleEntity()
    {
        return this.userRoleEntity;
    }

    public void setUserRoleEntity(Set<UserRoleEntity> userRoleEntity)
    {
        this.userRoleEntity = userRoleEntity;
    }

    public Set<GroupRoleEntity> getGroupRoleEntity()
    {
        return this.groupRoleEntity;
    }

    public void setGroupRoleEntity(Set<GroupRoleEntity> groupRoleEntity)
    {
        this.groupRoleEntity = groupRoleEntity;
    }

}