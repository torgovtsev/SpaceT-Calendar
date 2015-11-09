package com.github.teamcalendar.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "GROUP_ROLE", uniqueConstraints = @UniqueConstraint(columnNames = { "GROUP_ID", "ROLE_ID" }))
public class GroupRoleEntity
{

    @ManyToOne
    @JoinColumn(name = "GROUP_ID", nullable = false)
    private GroupEntity groupEntity;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private RoleEntity  roleEntity;

    public GroupRoleEntity()
    {
    }

    public GroupRoleEntity(GroupEntity groupEntity, RoleEntity roleEntity)
    {
        this.groupEntity = groupEntity;
        this.roleEntity = roleEntity;
    }

    public GroupEntity getGroupEntity()
    {
        return this.groupEntity;
    }

    public void setGroupEntity(GroupEntity groupEntity)
    {
        this.groupEntity = groupEntity;
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
