package com.github.teamcalendar.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "USER_ROLE", uniqueConstraints = @UniqueConstraint(columnNames = { "USER_ID", "ROLE_ID" }))
public class UserRoleEntity
{

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private RoleEntity roleEntity;

    public UserRoleEntity()
    {
    }

    public UserRoleEntity(UserEntity userEntity, RoleEntity roleEntity)
    {
        this.userEntity = userEntity;
        this.roleEntity = roleEntity;
    }

    public UserEntity getUserEntity()
    {
        return this.userEntity;
    }

    public void setUserEntity(UserEntity userEntity)
    {
        this.userEntity = userEntity;
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
