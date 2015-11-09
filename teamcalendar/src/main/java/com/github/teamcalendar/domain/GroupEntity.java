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
@Table(name = "GROUPS", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class GroupEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer              id;

    @Column(name = "NAME", unique = true, nullable = false, length = 64)
    private String               name;

    @Column(name = "DESCRIPTION", length = 512)
    private String               description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private Set<GroupRoleEntity> groupRoleEntity = new HashSet<GroupRoleEntity>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private Set<UserGroupEntity> userGroupEntity = new HashSet<UserGroupEntity>(0);

    public GroupEntity()
    {
    }

    public GroupEntity(String name)
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

    public Set<GroupRoleEntity> getGroupRoleEntity()
    {
        return this.groupRoleEntity;
    }

    public void setGroupRoleEntity(Set<GroupRoleEntity> groupRoleEntity)
    {
        this.groupRoleEntity = groupRoleEntity;
    }

    public Set<UserGroupEntity> getUserGroupEntity()
    {
        return this.userGroupEntity;
    }

    public void setUserGroupEntity(Set<UserGroupEntity> userGroupEntity)
    {
        this.userGroupEntity = userGroupEntity;
    }

}
