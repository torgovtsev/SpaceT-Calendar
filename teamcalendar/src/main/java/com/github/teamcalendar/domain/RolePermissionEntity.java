package com.github.teamcalendar.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ROLE_PERMISSION", uniqueConstraints = @UniqueConstraint(columnNames = { "ROLE_ID", "PERMISSION_ID" }))
public class RolePermissionEntity
{

    @ManyToOne
    @JoinColumn(name = "PERMISSION_ID", nullable = false)
    private PermissionEntity permissionEntity;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private RoleEntity       roleEntity;

    public RolePermissionEntity()
    {
    }

    public RolePermissionEntity(PermissionEntity permissionEntity, RoleEntity roleEntity)
    {
        this.permissionEntity = permissionEntity;
        this.roleEntity = roleEntity;
    }

    public PermissionEntity getPermissionEntity()
    {
        return this.permissionEntity;
    }

    public void setPermissionEntity(PermissionEntity permissionEntity)
    {
        this.permissionEntity = permissionEntity;
    }

    public RoleEntity getRoleEntity()
    {
        return this.roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity)
    {
        this.roleEntity = roleEntity;
    }

}
