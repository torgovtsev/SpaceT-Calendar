package com.github.teamcalendar.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.dozer.Mapping;

@Entity
@Table(name = "ROLE_", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class RoleEntity implements Serializable
{
    private static final long     serialVersionUID     = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    @Mapping("id")
    private Integer               id;

    @Column(name = "NAME", unique = true, nullable = false, length = 64)
    @Mapping("name")
    private String                name;

    @Column(name = "DESCRIPTION", length = 512)
    @Mapping("description")
    private String                description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ROLE_PERMISSION", joinColumns = { @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "PERMISSION_ID", nullable = false, updatable = false) })
    @Mapping("permissionRole")
    private Set<PermissionEntity> permissionRoleEntity = new HashSet<PermissionEntity>(0);

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "USER_ID", nullable = false, updatable = false) })
    @Mapping("userRole")
    private Set<UserEntity>       userRoleEntity       = new HashSet<UserEntity>(0);

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "GROUP_ROLE", joinColumns = { @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "GROUP_ID", nullable = false, updatable = false) })
    @Mapping("groupRole")
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

    public Set<PermissionEntity> getPermissionRoleEntity()
    {
        return this.permissionRoleEntity;
    }

    public void setPermissionRoleEntity(Set<PermissionEntity> permissionEntity)
    {
        this.permissionRoleEntity = permissionEntity;
    }

    public Set<UserEntity> getUserRoleEntity()
    {
        return this.userRoleEntity;
    }

    public void setUserRoleEntity(Set<UserEntity> userEntity)
    {
        this.userRoleEntity = userEntity;
    }

    public Set<GroupEntity> getGroupRoleEntity()
    {
        return this.groupRoleEntity;
    }

    public void setGroupRoleEntity(Set<GroupEntity> groupEntity)
    {
        this.groupRoleEntity = groupEntity;
    }

}
