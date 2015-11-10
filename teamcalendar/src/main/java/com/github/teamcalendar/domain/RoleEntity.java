package com.github.teamcalendar.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ROLE", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class RoleEntity implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer               id;

    @Column(name = "NAME", unique = true, nullable = false, length = 64)
    private String                name;

    @Column(name = "DESCRIPTION", length = 512)
    private String                description;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "rolePermissionEntity")
    private Set<PermissionEntity> permissionRoleEntity = new HashSet<PermissionEntity>(0);

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roleUserEntity")
    private Set<UserEntity>       userRoleEntity       = new HashSet<UserEntity>(0);

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "GROUP_ROLE", joinColumns = { @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "GROUP_ID", nullable = false, updatable = false) })
    private Set<GroupEntity>      groupRoleEntity      = new HashSet<GroupEntity>(0);

    public RoleEntity()
    {
    }

    public RoleEntity(String name)
    {
        this.name = name;
    }

    public RoleEntity(String name, Set<PermissionEntity> permissionRoleEntity, Set<UserEntity> userRoleEntity,
            Set<GroupEntity> groupRoleEntity)
    {
        this.name = name;
        this.permissionRoleEntity = permissionRoleEntity;
        this.userRoleEntity = userRoleEntity;
        this.groupRoleEntity = groupRoleEntity;
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

    public Set<PermissionEntity> getPermissionEntity()
    {
        return this.permissionRoleEntity;
    }

    public void setPermissionEntity(Set<PermissionEntity> permissionEntity)
    {
        this.permissionRoleEntity = permissionEntity;
    }

    public Set<UserEntity> getUserEntity()
    {
        return this.userRoleEntity;
    }

    public void setUserEntity(Set<UserEntity> userEntity)
    {
        this.userRoleEntity = userEntity;
    }

    public Set<GroupEntity> getGroupEntity()
    {
        return this.groupRoleEntity;
    }

    public void setGroupEntity(Set<GroupEntity> groupEntity)
    {
        this.groupRoleEntity = groupEntity;
    }

}
