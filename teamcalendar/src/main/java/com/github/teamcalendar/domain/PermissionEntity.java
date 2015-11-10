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
@Table(name = "PERMISSION", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class PermissionEntity implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer         id;

    @Column(name = "NAME", unique = true, nullable = false, length = 64)
    private String          name;

    @Column(name = "DESCRIPTION", length = 512)
    private String          description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ROLE_PERMISSION", joinColumns = { @JoinColumn(name = "PERMISSION_ID", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false) })
    private Set<RoleEntity> rolePermissionEntity = new HashSet<RoleEntity>(0);

    public PermissionEntity()
    {
    }

    public PermissionEntity(String name)
    {
        this.name = name;
    }

    public PermissionEntity(String name, Set<RoleEntity> roleEntity)
    {
        this.name = name;
        this.rolePermissionEntity = roleEntity;
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

    public Set<RoleEntity> getRoleEntity()
    {
        return this.rolePermissionEntity;
    }

    public void setRoleEntity(Set<RoleEntity> roleEntity)
    {
        this.rolePermissionEntity = roleEntity;
    }

}
