package com.github.teamcalendar.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "USER_GROUP", uniqueConstraints = @UniqueConstraint(columnNames = { "USER_ID", "GROUP_ID" }))
public class UserGroupEntity
{

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private UserEntity  userEntity;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID", nullable = false)
    private GroupEntity groupEntity;

    public UserGroupEntity()
    {
    }

    public UserGroupEntity(UserEntity userEntity, GroupEntity groupEntity)
    {
        this.userEntity = userEntity;
        this.groupEntity = groupEntity;
    }

    public UserEntity getUserEntity()
    {
        return this.userEntity;
    }

    public void setUserEntity(UserEntity userEntity)
    {
        this.userEntity = userEntity;
    }

    public GroupEntity getGroupEntity()
    {
        return this.groupEntity;
    }

    public void setGroupEntity(GroupEntity groupEntity)
    {
        this.groupEntity = groupEntity;
    }

}
