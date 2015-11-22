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
@Table(name = "GROUP_", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class GroupEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    @Mapping("id")
    private Integer           id;

    @Column(name = "NAME", unique = true, nullable = false, length = 64)
    @Mapping("name")
    private String            name;

    @Column(name = "DESCRIPTION", length = 512)
    @Mapping("description")
    private String            description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "GROUP_ROLE", joinColumns = { @JoinColumn(name = "GROUP_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false) })
    @Mapping("roleGroup")
    private Set<RoleEntity>   roleGroupEntity  = new HashSet<RoleEntity>(0);

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_Group", joinColumns = { @JoinColumn(name = "GROUP_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "USER_ID", nullable = false, updatable = false) })
    @Mapping("userGroup")
    private Set<UserEntity>   userGroupEntity  = new HashSet<UserEntity>(0);

    public GroupEntity()
    {
    }

    public GroupEntity(String name)
    {
        this.name = name;
    }

    public GroupEntity(String name, Set<RoleEntity> roleGroupEntity, Set<UserEntity> userGroupEntity)
    {
        this.name = name;
        this.roleGroupEntity = roleGroupEntity;
        this.userGroupEntity = userGroupEntity;

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

    public Set<RoleEntity> getRoleGroupEntity()
    {
        return this.roleGroupEntity;
    }

    public void setRoleGroupEntity(Set<RoleEntity> roleGroupEntity)
    {
        this.roleGroupEntity = roleGroupEntity;
    }

    public Set<UserEntity> getUserGroupEntity()
    {
        return this.userGroupEntity;
    }

    public void setUserGroupEntity(Set<UserEntity> userGroupEntity)
    {
        this.userGroupEntity = userGroupEntity;
    }

}
