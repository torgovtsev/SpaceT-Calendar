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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.dozer.Mapping;

@Entity
@Table(name = "PERMISSION", uniqueConstraints = @UniqueConstraint(columnNames = "NAME"))
public class PermissionEntity implements Serializable
{
    private static final long serialVersionUID     = 1L;

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

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "permissionRoleEntity")
    @Mapping("rolePermission")
    private Set<RoleEntity>   rolePermissionEntity = new HashSet<RoleEntity>(0);

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

    public Set<RoleEntity> getRolePermissionEntity()
    {
        return this.rolePermissionEntity;
    }

    public void setRolePermissionEntity(Set<RoleEntity> roleEntity)
    {
        this.rolePermissionEntity = roleEntity;
    }

}
